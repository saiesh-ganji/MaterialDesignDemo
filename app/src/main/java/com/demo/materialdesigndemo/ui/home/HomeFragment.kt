package com.demo.materialdesigndemo.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.materialdesigndemo.R
import com.demo.materialdesigndemo.fragments.BottomSheetListDemo
import com.demo.materialdesigndemo.views.CustomSpinner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val listener by lazy {
        object : CustomSpinner.OnItemSelectedListener {
            override fun onItemSelected(parent: CustomSpinner, view: View?, position: Int, id: Long) {
                Log.v("MaterialSpinner", "onItemSelected parent=${parent.id}, position=$position")
                parent.focusSearch(View.FOCUS_UP)?.requestFocus()
            }

            override fun onNothingSelected(parent: CustomSpinner) {
                Log.v("MaterialSpinner", "onNothingSelected parent=${parent.id}")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        val hintText = root.textInputLayout1.hint
        val asteriskred = resources.getString(R.string.asteriskred)
        root.textInputLayout.hint = "$hintText (*)"
        root.textInputLayout1.hint = "$hintText (Required)"
        root.textButton.setOnClickListener {
            root.textInputLayout1.error = "This is required"
            root.edittext.setText("Text")
        }
        activity?.let {
            ArrayAdapter.createFromResource(it, R.array.items, android.R.layout.simple_spinner_item).let {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                root.spinner.apply {
                    adapter = it
                    onItemSelectedListener = listener
                }
            }
        }
        root.outlineButton.setOnClickListener {
        MaterialAlertDialogBuilder(activity)
            .setTitle("Alert")
            .setMessage("Message homeViewModel.text.observe(viewLifecycleOwner, Observer")
            .setPositiveButton(
                "OK", null
            )
            .setNegativeButton(
                "Cancel", null
            )
            .show()
            .getButton(AlertDialog.BUTTON_POSITIVE)
            .setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
        val items = arrayOf("Item 1", "Item 2", "Item 3")
        val itemList = ArrayList<String>()
        for (i in 1..100){
            itemList.add("Item $i")
        }
        val checkedItems = booleanArrayOf(true, false, false, false)
        root.simpleDialogButton.setOnClickListener{
            MaterialAlertDialogBuilder(activity)
                .setTitle("Email")
                .setIcon(R.drawable.notifications_24)
                .setItems(items){dialog, which ->
                    Toast.makeText(activity, items.get(which),Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        root.confirmationButton.setOnClickListener{
            MaterialAlertDialogBuilder(activity)
                .setTitle("Items")
                .setMultiChoiceItems(items, checkedItems){dialog, which, checked ->
                }
                .show()
        }
        root.singleChoiceButton.setOnClickListener{
            MaterialAlertDialogBuilder(activity)
                .setTitle("Items")
                .setSingleChoiceItems(items, 0){dialog, which ->
                }
                .show()
        }
        root.openBottomSheetFragment.setOnClickListener{

            val bottomSheetListDemo = BottomSheetListDemo(context,itemList)
            bottomSheetListDemo.show(activity?.supportFragmentManager!!,"")
            val bottomSheetListDemoDialog = bottomSheetListDemo.dialog

        }


        return root
    }
}

