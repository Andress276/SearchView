package com.example.searchview

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var expenseList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpList()
    }

    private fun setUpList() {
        expenseList = findViewById(R.id.list)

        val adapter = ExpenseAdapter()
        expenseList.adapter = adapter

        adapter.submitList(ExpenseRepository.getAll())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.expenses_menu, menu)

        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(
            sm.getSearchableInfo(
                ComponentName(this, MainActivity::class.java)
            )
        )
        return true
    }
}