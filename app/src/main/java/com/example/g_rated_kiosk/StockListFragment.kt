package com.example.g_rated_kiosk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.databinding.FragmentStockListBinding

class StockListFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stock_list, container, false)
        val left = view.findViewById<RecyclerView>(R.id.leftItems)
        val right = view.findViewById<RecyclerView>(R.id.rightItems)

        right.layoutManager = LinearLayoutManager(view.context)
        left.layoutManager = LinearLayoutManager(view.context)
        left.adapter = StockItemsAdapter(MenuStocks.soldout)
        right.adapter = StockItemsAdapter(MenuStocks.notSoldout)

        return view
    }
}