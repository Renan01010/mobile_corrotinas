package br.ufpr.cronometro
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var tvTime: TextView
    private lateinit var btnStart: Button

    private var counterJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        tvTime = findViewById(R.id.tvTime)
        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            startCounter()
        }
    }

    // Função que inicia o cronômetro
    private fun startCounter() {

        // Evita iniciar várias corrotinas simultaneamente
        if (counterJob?.isActive == true) return

        counterJob = lifecycleScope.launch(Dispatchers.Main) {

            // Atualiza o contador de 1 até 10
            for (i in 1..10) {

                tvTime.text = i.toString()

                // Espera 1 segundo
                delay(1000L)
            }

            // Finaliza a contagem
            btnStart.text = "Iniciar novamente"
        }
    }
}
