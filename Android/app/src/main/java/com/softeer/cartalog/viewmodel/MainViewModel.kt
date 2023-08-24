package com.softeer.cartalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.cartalog.data.repository.CarRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CarRepository) : ViewModel() {

    private val _stepIndex = MutableLiveData(0)
    val stepIndex: LiveData<Int> = _stepIndex
    private val _budgetRangeLimit = MutableLiveData(0)
    val budgetRangeLimit: LiveData<Int> = _budgetRangeLimit
    private val _budgetRangeLimitProgress = MutableLiveData(50)
    val budgetRangeLimitProgress: LiveData<Int> = _budgetRangeLimitProgress
    private val _totalPrice = MutableLiveData(0)
    val totalPrice: LiveData<Int> = _totalPrice
    private val _totalPriceProgress = MutableLiveData<Int>(0)
    val totalPriceProgress: LiveData<Int> = _totalPriceProgress
    private val _isExcess = MutableLiveData(false)
    val isExcess: LiveData<Boolean> = _isExcess

    private val _minPrice = MutableLiveData(0)
    val minPrice: LiveData<Int> = _minPrice
    private val _maxPrice = MutableLiveData(0)
    val maxPrice: LiveData<Int> = _maxPrice
    private var priceRange = 0

    private val _isReset = MutableLiveData<Boolean>(false)
    val isReset: LiveData<Boolean> = _isReset

    fun setStepIndex(index: Int) {
        _stepIndex.value = index
    }

    fun setRangeLimit(progress: Int) {
        _budgetRangeLimitProgress.value = progress
        _budgetRangeLimit.value = calculatePriceFromProgress(progress)
        _isExcess.value = budgetRangeLimit.value!! < totalPrice.value!!
    }

    fun setMinMaxPrice(minPrice: Int, maxPrice: Int) {
        _minPrice.value = minPrice
        _maxPrice.value = maxPrice
        priceRange = maxPrice - minPrice
    }

    fun setTotalPriceProgress(total: Int) {
        _totalPrice.value = total
        _totalPriceProgress.value = calculateProgressFromPrice(totalPrice.value!!)
    }

    private fun calculateProgressFromPrice(price: Int): Int {
        val adjustedValue = price - minPrice.value!!
        return (adjustedValue.toFloat() / priceRange.toFloat() * 100).toInt()
    }

    private fun calculatePriceFromProgress(progress: Int): Int {
        return minPrice.value!! + (priceRange * progress / 100)
    }

    fun setInitPriceData(total: Int) {
        _totalPrice.value = total
        _totalPriceProgress.value = calculateProgressFromPrice(totalPrice.value!!)
        _budgetRangeLimit.value = calculatePriceFromProgress(50)
    }

    fun changeResetState() {
        _isReset.value = !_isReset.value!!
        viewModelScope.launch {
            repository.deleteAllData()
        }
    }
}