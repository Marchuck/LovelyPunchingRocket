package pl.marchuck.lovelypunchingrocket.signIn;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.reactivex.Observable;

public class RxUtils {

    private RxUtils() {
    }

    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return Observable.create(e -> {

            final android.databinding.Observable.OnPropertyChangedCallback callback =
                    new android.databinding.Observable.OnPropertyChangedCallback() {
                        @Override
                        public void onPropertyChanged(android.databinding.Observable observable,
                                                      int propertyId) {

                            if (observable == observableField) {
                                T value = observableField.get();
                                if (value != null) {
                                    e.onNext(value);
                                }
                            }
                        }
                    };
            e.setCancellable(() -> observableField.removeOnPropertyChangedCallback(callback));

            observableField.addOnPropertyChangedCallback(callback);
        });
    }
}
