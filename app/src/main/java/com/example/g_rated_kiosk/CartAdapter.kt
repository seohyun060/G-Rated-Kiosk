
package com.example.g_rated_kiosk

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ActivityMenuSelectBinding
import com.example.g_rated_kiosk.databinding.ItemBinding


class CartAdapter (val cartList: MutableList<cart>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
/* inflate(int resource, ViewGroup parent, boolean attachToRoot)
* - attachToRoot= false: parent is only used to create the correct subclass of LayoutParams
*/
        return MyViewHolder(ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }
    // onCreateViewHolder()에서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder()의 매개변수로 전달
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemBinding = (holder as MyViewHolder).binding
        val item = cartList[position]
        itemBinding.itemImage.setImageDrawable(item.menu?.MenuImage);
        itemBinding.itemMenuName.text= item.menu?.Name
        itemBinding.itemMenuCount.text=item.count.toString()
        itemBinding.itemSideName.text= item.side?.Name?:""
        itemBinding.itemDrinkName.text= item.drink?.Name?:""

        if(item.menu?.Type != MenuType.BURGER){
            itemBinding.itemNoOnion.visibility=INVISIBLE;
            itemBinding.itemNoLettuce.visibility=INVISIBLE;
            itemBinding.itemNoPickle.visibility=INVISIBLE;
        }
        else {
            itemBinding.itemNoOnion.visibility=VISIBLE;
            itemBinding.itemNoLettuce.visibility=VISIBLE;
            itemBinding.itemNoPickle.visibility=VISIBLE;
            if (cartList[position].noLettuce) {
                itemBinding.itemNoLettuce.setTextColor(Color.parseColor("#d32f2f"));
                itemBinding.itemNoLettuce.paintFlags =
                    itemBinding.itemNoLettuce.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else{
                itemBinding.itemNoLettuce.setTextColor(Color.parseColor("#8A000000"))
                itemBinding.itemNoLettuce.paintFlags =
                    itemBinding.itemNoLettuce.paintFlags and (itemBinding.itemNoLettuce.paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG)
            }
            if (cartList[position].noOnion) {
                itemBinding.itemNoOnion.setTextColor(Color.parseColor("#d32f2f"));
                itemBinding.itemNoOnion.paintFlags =
                    itemBinding.itemNoOnion.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else{
                itemBinding.itemNoOnion.setTextColor(Color.parseColor("#8A000000"))
                itemBinding.itemNoOnion.paintFlags =
                    itemBinding.itemNoOnion.paintFlags and (itemBinding.itemNoOnion.paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG)
            }
            if (cartList[position].noPickle) {
                itemBinding.itemNoPickle.setTextColor(Color.parseColor("#d32f2f"));
                itemBinding.itemNoPickle.paintFlags =
                    itemBinding.itemNoPickle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else{
                itemBinding.itemNoPickle.setTextColor(Color.parseColor("#8A000000"))
                itemBinding.itemNoPickle.paintFlags =
                    itemBinding.itemNoPickle.paintFlags and (itemBinding.itemNoPickle.paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG)
            }
        }
        itemBinding.itemRoot.setOnClickListener { // 뷰에 이벤트 추가
        }
      itemBinding.itemAdd.setOnClickListener { item.count = (item.count+1).coerceAtMost(MenuView.quantityThreshold)
          notifyItemChanged(position)
      }
        itemBinding.itemSub.setOnClickListener { if (item.count>1){
            item.count -=1
            notifyItemChanged(position)
        }
            else {
                cartList.removeAt(position)
                notifyDataSetChanged()
        }
        }

    }


    override fun getItemCount(): Int {
        return cartList.size
    }
}