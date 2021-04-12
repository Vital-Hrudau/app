package io.techmeskills.an02onl_plannerapp.screen.add_new

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.techmeskills.an02onl_plannerapp.R
import io.techmeskills.an02onl_plannerapp.databinding.FragmentAddNewBinding
import io.techmeskills.an02onl_plannerapp.support.NavigationFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddNewFragment : NavigationFragment<FragmentAddNewBinding>(R.layout.fragment_add_new) {
    override val viewBinding: FragmentAddNewBinding by viewBinding()

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
        viewBinding.toolbar.setPadding(0, top, 0, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentDate = Date()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateText: String = dateFormat.format(currentDate)
        viewBinding.editData.setText(dateText)
        viewBinding.btnAddNew.setOnClickListener {
            if(viewBinding.editNewNote.text.isNotBlank()){
            val bundle = bundleOf(
                "txt_Note" to viewBinding.editNewNote.text.toString(),
                "txt_Data" to viewBinding.editData.text.toString()
            )
            setFragmentResult("requestKey", bundle)
                findNavController().popBackStack()
        } else{
                val toast = Toast.makeText(context, "Поле ввода заметки пусто", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    override val backPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
}