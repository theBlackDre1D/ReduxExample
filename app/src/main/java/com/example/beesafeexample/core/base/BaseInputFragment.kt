package com.example.beesafeexample.core.base

abstract class BaseInputFragment<P: BaseInputFragment.BaseParams>(open var params: P?): BaseFragment() {

    abstract class BaseParams
}