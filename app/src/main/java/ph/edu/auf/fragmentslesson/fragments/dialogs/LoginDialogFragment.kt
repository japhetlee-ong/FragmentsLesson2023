package ph.edu.auf.fragmentslesson.fragments.dialogs

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import ph.edu.auf.fragmentslesson.R
import ph.edu.auf.fragmentslesson.databinding.DialogLoginBinding
import ph.edu.auf.fragmentslesson.viewmodels.LoginViewModel

class LoginDialogFragment : DialogFragment(), OnClickListener {

    private lateinit var binding: DialogLoginBinding
    private lateinit var onLoginCallback: LoginDialogInterface
    //SECOND PART OF DIALOGFRAGMENT
    private lateinit var loginViewModel: LoginViewModel

    interface LoginDialogInterface{
        fun sendData(username: String, password: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onLoginCallback = context as LoginDialogInterface

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener(this)
        binding.btnClose.setOnClickListener(this)
        //SECOND PART OF DIALOGFRAGMENT LESSON
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.setCancelable(false)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_login) -> {
               // onLoginCallback.sendData(binding.username.text.toString(),binding.password.text.toString())
                //SECOND PART OF THE DIALOGFRAGMENT LESSON
                loginViewModel.sendDetails(binding.username.text.toString(),binding.password.text.toString())
                dialog!!.dismiss()

            }
            (R.id.btn_close) -> {
                dialog!!.dismiss()
            }
        }
    }
}