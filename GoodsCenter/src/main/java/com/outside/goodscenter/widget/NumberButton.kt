package com.outside.goodscenter.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.EditText
import android.view.LayoutInflater
import android.text.method.DigitsKeyListener
import com.outside.baselibrary.ext.onClick
import com.outside.goodscenter.R
import kotlinx.android.synthetic.main.layout_number_button.view.*
import kotlin.math.min



/**
 * className:    NumberButton
 * description:  购物车商品数量、增加和减少控制按钮。
 * author:       CLW2018
 * creatTime:    2019/9/6 14:49
 */

class NumberButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener, TextWatcher {


    //库存
    private var mInventory = Integer.MAX_VALUE
    //最大购买数，默认无限制
    private var mBuyMax = Integer.MAX_VALUE

    lateinit var  mCount: EditText

    private lateinit var mOnWarnListener: OnWarnListener

    private lateinit var mRootView: View

    init {

        mRootView = LayoutInflater.from(context).inflate(R.layout.layout_number_button, this)

        mRootView.button_add.onClick(this)
        mRootView.button_sub.onClick(this)
        mCount = mRootView.text_count
        mCount.onClick(this)
        mCount.addTextChangedListener(this)


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberButton)
        val editable = typedArray.getBoolean(R.styleable.NumberButton_editable, true)
        val buttonWidth = typedArray.getDimensionPixelSize(R.styleable.NumberButton_buttonWidth, -1)
        val textWidth = typedArray.getDimensionPixelSize(R.styleable.NumberButton_textWidth, -1)
        val textSize = typedArray.getDimensionPixelSize(R.styleable.NumberButton_textSize, -1)
        val textColor = typedArray.getColor(R.styleable.NumberButton_textColor, -0x1000000)
        typedArray.recycle()

        setEditable(editable)
        mCount.setTextColor(textColor)
        mRootView.button_sub.setTextColor(textColor)
        mRootView.button_add.setTextColor(textColor)

        if (textSize > 0)
            mCount.textSize = textSize.toFloat()

        if (buttonWidth > 0) {
            val textParams =
                LinearLayout.LayoutParams(buttonWidth, LinearLayout.LayoutParams.MATCH_PARENT)
            mRootView.button_sub.layoutParams = textParams
            mRootView.button_add.layoutParams = textParams
        }
        if (textWidth > 0) {
            val textParams =
                LinearLayout.LayoutParams(textWidth, LinearLayout.LayoutParams.MATCH_PARENT)
            mCount.layoutParams = textParams
        }

    }


    fun getNumber(): Int {
        try {
            return Integer.parseInt(mCount.text.toString())
        } catch (e: NumberFormatException) {
        }

        mCount.setText("1")

        return 1
    }

    private fun setEditable(editable: Boolean) {
        if (editable) {
            mCount.isFocusable = true
            mCount.keyListener = DigitsKeyListener()
        } else {
            mCount.isFocusable = false
            mCount.keyListener = null
        }

    }

    override fun onClick(p0: View) {
        val count = getNumber()
        when (p0.id) {
            R.id.button_add -> {
                if (count < min(mBuyMax, mInventory)) {
                    //正常添加
                    mCount.setText("" + (count + 1))
                } else if (mInventory < mBuyMax) {
                    //库存不足
                    warningForInventory()
                } else {
                    //超过最大购买数
                    warningForBuyMax()
                }
            }
            R.id.button_sub -> {
                if (count > 1) {
                    //正常减
                    mCount.setText("" + (count - 1))
                }
            }
            R.id.text_count -> {
                mCount.setSelection(mCount.text.length)
            }

        }

    }

    private fun onNumberInput() {
        //当前数量
        val count = getNumber()
        if (count <= 0) {
            //手动输入
            mCount.setText("1")
            return
        }

        val limit = min(mBuyMax, mInventory)
        if (count > limit) {
            //超过了数量
            mCount.setText(limit.toString() + "")
            if (mInventory < mBuyMax) {
                //库存不足
                warningForInventory()
            } else {
                //超过最大购买数
                warningForBuyMax()
            }
        }

    }

    /**
     * 超过的库存限制
     * Warning for inventory.
     */
    private fun warningForInventory() {
        if (mOnWarnListener != null) mOnWarnListener.onWarningForInventory(mInventory)
    }

    /**
     * 超过的最大购买数限制
     * Warning for buy max.
     */
    private fun warningForBuyMax() {
        if (mOnWarnListener != null) mOnWarnListener.onWarningForBuyMax(mBuyMax)
    }


    fun setCurrentNumber(currentNumber: Int): NumberButton {
        if (currentNumber < 1) mCount.setText("1")
        mCount.setText("" + min(min(mBuyMax, mInventory), currentNumber))
        return this
    }

    fun getInventory(): Int {
        return mInventory
    }

    fun setInventory(inventory: Int): NumberButton {
        mInventory = inventory
        return this
    }

    fun getBuyMax(): Int {
        return mBuyMax
    }

    fun setBuyMax(buyMax: Int): NumberButton {
        mBuyMax = buyMax
        return this
    }

    fun setOnWarnListener(onWarnListener: OnWarnListener): NumberButton {
        mOnWarnListener = onWarnListener
        return this
    }

    interface OnWarnListener {

        fun onWarningForInventory(inventory: Int)

        fun onWarningForBuyMax(max: Int)
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onNumberInput()
    }


}