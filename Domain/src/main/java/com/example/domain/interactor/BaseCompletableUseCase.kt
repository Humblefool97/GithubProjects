package com.example.domain.interactor

import com.example.domain.executer.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseCompletableUseCase<in Params> constructor(private val postExecutionThread: PostExecutionThread) {
    val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    fun execute(observr: DisposableCompletableObserver, params: Params? = null) {
        val completable = this.buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        addDisposable(completable.subscribeWith(observr))
    }

    fun addDisposable(disposable: Disposable) = disposables.add(disposable)

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }
}