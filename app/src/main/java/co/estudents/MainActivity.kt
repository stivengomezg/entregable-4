package co.estudents

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import co.estudents.base.Resource
import co.estudents.data.Estudent
import co.estudents.data.Estudents
import co.estudents.data.RetrofitClient
import co.estudents.data.WebService
import co.estudents.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: WebService
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = RetrofitClient.estudentService
        getEstudents()
    }

    fun getEstudents() {

        get().observe(this, Observer {
            when(it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val adapter = EstudentAdapter(it.data)
                    binding.rcv.adapter = adapter
                }
                is Resource.Failure -> {
                    Toast.makeText(this, "Fallo ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun get() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(getAllEstudents())
        }catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    suspend fun getAllEstudents(): Resource<List<Estudent>> = try {
        Resource.Success(retrofit.getEstudents())
    }catch (e: Exception) {
        Resource.Failure(e)
    }
}