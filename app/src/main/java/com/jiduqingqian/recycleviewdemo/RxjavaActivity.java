package com.jiduqingqian.recycleviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jiduqingqian.recycleviewdemo.module.User;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class RxjavaActivity extends Activity {
    private TextView observableTv, flowableTv;
    private StringBuffer sringBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        observableTv = (TextView) findViewById(R.id.observable);
        flowableTv = (TextView) findViewById(R.id.flowable);
        observableDemo();
        flowableDemo();


        List<User> users = Arrays.asList(new User("张三", 23), new User("李四", 53));

        //just 将对象或者对象集合转换为一个会发射这些对象的Observable
        Observable
                .fromArray(new User("张三", 23), new User("李四", 53), new User("王二麻子", 63))
                .map(user -> {
                    if (user.getAge() == 23) {
                        user.setAge(12);
                        user.setName("张三23");
                    }
                    return user;
                })
                .filter(user -> {
                    if (user.getAge() == 23) {
                        return false;
                    }
                    return true;
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(user -> {
            sringBuffer.append(user.getName() + "\n");
            flowableTv.setText(sringBuffer);
        });

    }

    private void observableDemo() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onComplete();
            }
        });
        Observer<String> observer = new Observer<String>() {
            Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                sringBuffer.append(s + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                sringBuffer.append("onError" + "\n");
            }

            @Override
            public void onComplete() {
                Log.d("qh", "onComplete");
                sringBuffer.append("onComplete");
                observableTv.setText(sringBuffer);
            }
        };

        observableTv.setOnClickListener(v ->
                observable
                        .observeOn(Schedulers.newThread())
                        .map(new Function<String, String>() {
                            @Override
                            public String apply(@NonNull String s) throws Exception {
                                return s + "  map1=" + Thread.currentThread().getName();
                            }
                        })
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(@NonNull String s) throws Exception {
                                if (s.contains("2")) {
                                    return false;
                                }
                                return true;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer)
        );
    }

    private void flowableDemo() {
        ArrayList<String[]> list = new ArrayList<>();
        String[] words1 = {"Hello,", "I am", "China!"};
        String[] words2 = {"Hello,", "I am", "Beijing!"};
        list.add(words1);
        list.add(words2);
        Flowable
                .fromIterable(list)
                //一对一对象 或 一对多对象展开
                .flatMap(new Function<String[], Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull String[] strings) throws Exception {
                        return Flowable.fromArray(strings);
                    }
                })
                // 一对一对象展开
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {

                        return s + " fuck";
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String o) throws Exception {
                Log.d("qh", o);
            }
        });

    }
}
