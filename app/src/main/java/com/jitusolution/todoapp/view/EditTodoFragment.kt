package com.jitusolution.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jitusolution.todoapp.R
import com.jitusolution.todoapp.databinding.FragmentEditTodoBinding
import com.jitusolution.todoapp.model.Todo
import com.jitusolution.todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*


class EditTodoFragment : Fragment(),RadioClickListener,TodoSaveChangesListener {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,R.layout.fragment_edit_todo,container,false)
        //return inflater.inflate(R.layout.fragment_create_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //cara menginisialisasi View Model
        viewModel= ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        //cara memanggil uuid
        val uuid= EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        dataBinding.radiolistener = this
        //databinding harus di instantiate
        dataBinding.listener=this
//        txtJudulTodo.text="Edit Todoo"
//        btnCreateToDo.text="Save Changes"
//        btnCreateToDo.setOnClickListener {
//            val radio =
//                view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todoo updated", Toast.LENGTH_SHORT).show()
//        }

        observeViewModel()
    }
    fun observeViewModel()
    {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
        /*        txtTitle.setText(it.title)
            txtNotes.setText(it.notes)


            when(it.priority)
            {
                1->radioLow.isChecked= true
                2->radioMedium.isChecked= true
                else->radioHigh.isChecked= true
            } */

//            if(it.priority == 3)
//            {
//                radioHigh.isChecked=true
//            }
//            else if(it.priority == 2)
//            {
//                radioMedium.isChecked=true
//            }
//            else
//            {
//                radioLow.isChecked=true
//            }
        })
    }

    override fun onRadioClick(v: View, obj: Todo) {
        obj.priority = v.tag.toString().toInt()

    }

    override fun onTodoSaveChanges(v: View, obj: Todo) {
        Log.d("cobacek",obj.toString())
        viewModel.update(obj.title,obj.notes,obj.priority,obj.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }


}