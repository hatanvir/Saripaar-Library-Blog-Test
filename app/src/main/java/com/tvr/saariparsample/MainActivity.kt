package com.tvr.saariparsample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password


class MainActivity : AppCompatActivity(),Validator.ValidationListener{
    @NotEmpty
    @BindView(R.id.emailEt)
    lateinit var emailEt: EditText

    @Password(min = 6)
    @BindView(R.id.passwordEt)
    lateinit var passwordEt: EditText

    @BindView(R.id.loginBt)
    lateinit var loginBt: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        val validator = Validator(this)
        validator.setValidationListener(this)

        loginBt.setOnClickListener {
            validator.validate()
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        for (error in errors!!) {
            val view: View = error.view//finding error view
            val message = error.getCollatedErrorMessage(this)

            if (view is EditText) {
                (view as EditText).error = message//setting error message
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onValidationSucceeded() {

    }
}