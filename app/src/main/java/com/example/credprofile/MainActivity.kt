package com.example.credprofile

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    data class ProfileRow(val label: String, val value: String, val valueColorHex: String = "#FFFFFF")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scrollView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Profile section
        val username = findViewById<TextView>(R.id.username)
        val memberSince = findViewById<TextView>(R.id.member_since)
        val editButton = findViewById<ImageView>(R.id.edit_button)

        username.text = "Andaz Kumar"
        memberSince.text = "member since Dec, 2020"
        // You can load a real image here with Glide or Coil if needed

        // Setup rows
        setupProfileRow(R.id.credit_score_row, ProfileRow("credit score", "757"))
        setupProfileRow(R.id.lifetime_cashback_row, ProfileRow("lifetime cashback", "₹1,250"))
        setupProfileRow(R.id.bank_balance_row, ProfileRow("bank balance", "check"))

        // Optional: handle button click
        editButton.setOnClickListener {
            // show edit profile screen or toast
        }
    }

    private fun setupProfileRow(rowId: Int, data: ProfileRow) {
        val row = findViewById<android.view.View>(rowId)
        val label = row.findViewById<TextView>(R.id.label)
        val value = row.findViewById<TextView>(R.id.value)
        val icon = row.findViewById<ImageView>(R.id.icon)
        val refreshHint = row.findViewById<TextView>(R.id.refresh_hint)

        val iconRes = when (data.label.lowercase()) {
            "credit score" -> R.drawable.ic_credit
            "lifetime cashback" -> R.drawable.is_ruppes
            "bank balance" -> R.drawable.ic_fast
            else -> R.drawable.is_ruppes // fallback icon
        }

        icon.setImageResource(iconRes)
        label.text = data.label
        value.text = data.value
        value.setTextColor(android.graphics.Color.parseColor(data.valueColorHex))

        // Show refresh hint only for "credit score"
        if (data.label.equals("credit score", ignoreCase = true)) {
            refreshHint.visibility = android.view.View.VISIBLE
            refreshHint.text = "• REFRESH AVAILABLE"
            refreshHint.setTextColor(android.graphics.Color.parseColor("#449168"))
        } else {
            refreshHint.visibility = android.view.View.GONE
        }
    }
}