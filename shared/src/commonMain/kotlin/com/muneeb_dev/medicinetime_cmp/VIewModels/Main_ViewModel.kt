package com.muneeb_dev.medicinetime_cmp.VIewModels


import com.muneeb_dev.medicinetime_cmp.Expect_.DatabaseDriverFactory
import com.muneeb_dev.medicinetime_cmp.database.MedicineDatabase
import database.Patient_medicines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.mp.KoinPlatform

class Main_ViewModel : ViewModel() {


    private val factory: DatabaseDriverFactory = KoinPlatform.getKoin().get()

    private val database = MedicineDatabase(factory.createDriver())

    private val medicineQueries = database.mediciene_sqlQueries

    val CreateTable = medicineQueries.createTable()


    private val _allItems = MutableStateFlow<List<Patient_medicines>>(emptyList())
    val allItems: StateFlow<List<Patient_medicines>> get() = _allItems.asStateFlow()



    private val _myMediciensList = MutableStateFlow(listOf<Patient_medicines>())
    val myMediciensList: StateFlow<List<Patient_medicines>> get() = _myMediciensList


    private val _revealedCardIdsList = MutableStateFlow(listOf<String>())
    val revealedCardIdsList: StateFlow<List<String>> get() = _revealedCardIdsList


    fun onItemExpanded(cardId: String) {
        if (_revealedCardIdsList.value.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
            list.add(cardId)
            println("onItemExpanded--> $cardId added ")

        }


    }

    fun onItemCollapsed(cardId: String) {
        if (!_revealedCardIdsList.value.contains(cardId)) return
        _revealedCardIdsList.value = _revealedCardIdsList.value.toMutableList().also { list ->
            list.remove(cardId)
            println("onItemCollapsedDragAmount-->--> $cardId removed ")

        }
    }


    fun CreateTable()
    {
        return medicineQueries.createTable()
    }


    fun getAllItems() {
        // Fetch data from your SQLDelight query and update the StateFlow
        _allItems.value = medicineQueries.selectAll().executeAsList()
    }


    fun deleteEntry(medicineName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Perform deletion asynchronously on IO thread
            medicineQueries.DeleteMedicine(medicineName)
            // Fetch and update the list after deletion on the IO thread
            val updatedList = medicineQueries.selectAll().executeAsList()
            println("updatedList--> ${updatedList.size}")
            // Update the UI on the main thread
//            launch(Dispatchers.Main) {
                _allItems.value = emptyList()
                _allItems.value = updatedList
//            }
        }
    }
}
