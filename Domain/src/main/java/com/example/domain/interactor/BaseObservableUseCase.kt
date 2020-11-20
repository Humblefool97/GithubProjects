package com.example.domain.interactor

import com.example.domain.executer.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseObservableUseCase<T, in Params> constructor(private val postExecutionThread: PostExecutionThread) {

    private val disposbales = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(singleObserver: DisposableObserver<T>, params: Params? = null) {
        val single = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

    }

    fun addDisposable(disposable: Disposable) = disposbales.add(disposable)

    fun dispose() {
        if (!disposbales.isDisposed)
            disposbales.dispose()
    }
}