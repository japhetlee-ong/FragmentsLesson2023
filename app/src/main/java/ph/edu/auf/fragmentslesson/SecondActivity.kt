package ph.edu.auf.fragmentslesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ph.edu.auf.fragmentslesson.databinding.ActivitySecondBinding
import ph.edu.auf.fragmentslesson.fragments.FragmentOne
import ph.edu.auf.fragmentslesson.fragments.FragmentTwo
import ph.edu.auf.fragmentslesson.fragments.LifecycleFragment
import ph.edu.auf.fragmentslesson.fragments.SendDataFragment
import ph.edu.auf.fragmentslesson.fragments.dialogs.BasicDialogFragment
import ph.edu.auf.fragmentslesson.fragments.dialogs.LoginDialogFragment
import ph.edu.auf.fragmentslesson.viewmodels.LoginViewModel

class SecondActivity : AppCompatActivity(), OnClickListener, LoginDialogFragment.LoginDialogInterface {
    private lateinit var binding: ActivitySecondBinding
    private val TAG : String = SecondActivity::class.java.simpleName
    //SECOND PART OF THE DIALOGFRAGMENT LESSON
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowFragmentOne.setOnClickListener(this)
        binding.btnShowFragmentTwo.setOnClickListener(this)
        binding.btnSendDataToFragment.setOnClickListener(this)
        binding.btnViewFragmentLs.setOnClickListener(this)
        binding.btnShowDialog.setOnClickListener(this)
        binding.btnShowDialogFragment.setOnClickListener(this)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.username.observe(this, Observer {
            binding.txtUsername.text = it
        })
        loginViewModel.password.observe(this, Observer {
            binding.txtPassword.text = it
        })

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_show_fragment_one)->{
                val fragmentManager = supportFragmentManager.beginTransaction()
                fragmentManager.replace(binding.fragmentContainerView.id,FragmentOne())
                fragmentManager.commit()
            }
            (R.id.btn_show_fragment_two) ->{
                val fragmentManager = supportFragmentManager.beginTransaction()
                fragmentManager.replace(binding.fragmentContainerView.id,FragmentTwo())
                fragmentManager.commit()
            }
            (R.id.btn_send_data_to_fragment) -> {
                val sendDataFragment = SendDataFragment.newInstance("Hello there", "From Second Activity")
                val fragmentManager = supportFragmentManager.beginTransaction()
                fragmentManager.replace(binding.fragmentContainerView.id,sendDataFragment)
                fragmentManager.commit()
            }
            (R.id.btn_view_fragment_ls) ->{
                val fragmentManager = supportFragmentManager.beginTransaction()
                fragmentManager.replace(binding.fragmentContainerView.id,LifecycleFragment())
                fragmentManager.commit()
            }
            (R.id.btn_show_dialog) ->{
                //Simple Dialog
                MaterialAlertDialogBuilder(this)
                    .setTitle("Dialog title")
                    .setMessage("This is a message")
                    .setIcon(R.drawable.ic_email)
                    .setPositiveButton("Confirm") {dialog, which ->
                        Log.d(TAG,"Confirm clicked")
                    }
                    .setNegativeButton("Cancel") {dialog, which ->
                        Log.d(TAG,"Cancel clicked")
                    }
                    .setNeutralButton("Neutral") {dialog, which ->
                        Log.d(TAG,"Neutral clicked")
                    }
                    .show()

                //Single choice items
//                val gradeChoice = arrayOf("100","95","90","85","80")
//                MaterialAlertDialogBuilder(this)
//                    .setTitle("Set the grade of Jericho Diaz")
//                    .setItems(gradeChoice) {dialog, which ->
//                        Log.d(TAG,"Selected the grade: ${gradeChoice[which]}")
//                    }
//                    .show()

                //Single choice with confirm
//                val gradeChoice = arrayOf("100","95","90","85","80")
//                var selectedGrade = 0
//                MaterialAlertDialogBuilder(this)
//                    .setTitle("Set the grade of Jericho Diaz")
//                    .setSingleChoiceItems(gradeChoice,selectedGrade) {dialog, which ->
//                        Log.d(TAG,"Selected the grade: ${gradeChoice[which]}")
//                        selectedGrade = which
//                    }
//                    .setPositiveButton("Confirm"){dialog, which ->
//                        Log.d(TAG,"The final selected grade: ${gradeChoice[selectedGrade]}")
//                    }
//                    .setNegativeButton("Cancel"){dialog, which ->
//
//                    }
//                    .show()

                //Multi-choice items
//                val multiItems = arrayOf("100","95","90","85","80")
//                val checkedItems = booleanArrayOf(false, false, false, false,false)
//                val selectedItems = ArrayList<String>()
//                    MaterialAlertDialogBuilder(this)
//                    .setTitle("Set the recitation grades of Jericho Diaz")
//                    .setMultiChoiceItems(multiItems, checkedItems) { dialog, which, checked ->
//                        if(checked){
//                            selectedItems.add(multiItems[which])
//                        }else if(selectedItems.contains(multiItems[which])){
//                            selectedItems.remove(multiItems[which])
//                        }
//                    }
//                    .setPositiveButton("Confirm"){dialog, which ->
//                        Log.d(TAG,"Final selected recitation grades are: $selectedItems")
//                    }
//                    .setNegativeButton("Cancel"){dialog,which ->
//
//                    }
//                    .show()

            }
            (R.id.btn_show_dialog_fragment) -> {
                //BasicDialogFragment().show(supportFragmentManager,BasicDialogFragment.TAG)
                LoginDialogFragment().show(supportFragmentManager,"Login")
            }
        }
    }

    override fun sendData(username: String, password: String) {
        binding.txtPassword.text = password
        binding.txtUsername.text = username
    }
}