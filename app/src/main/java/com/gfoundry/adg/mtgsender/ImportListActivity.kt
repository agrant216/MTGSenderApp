package com.gfoundry.adg.mtgsender

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.gfoundry.adg.mtgsender.adapters.ImportListAdapter
import com.gfoundry.adg.mtgsender.models.Card
import java.io.IOException
import android.support.v7.widget.DividerItemDecoration

class ImportListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_list)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv.setHasFixedSize(true)

        val cards = ArrayList<Card>()

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent, cards) // Handle text being sent
                }
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }
        val adapter = ImportListAdapter(cards)
        rv.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(this,LinearLayout.VERTICAL)
        rv.addItemDecoration(dividerItemDecoration)
    }

    private fun handleSendText(intent: Intent,cardList:ArrayList<Card>) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            try {
                val lines: List<String> = it.lines()

                for((index,value) in lines.withIndex()){
                    if (index > 0 && value != ""){
                        val tokens = value.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)".toRegex(),0)
                        cardList.add(Card(tokens[0].toIntOrNull(),tokens[1].replace("\"",""),tokens[2],tokens[3].toIntOrNull(),tokens[4]))
                    }
                }
            } catch (e: Exception) {
                println("Reading CSV Error!")
                e.printStackTrace()
            } finally {
                try {
                } catch (e: IOException) {
                    println("Closing fileReader Error!")
                    e.printStackTrace()
                }
            }
        }
    }
}