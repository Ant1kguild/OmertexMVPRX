package com.omertex.mvprx.presentation.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.api.load
import coil.request.CachePolicy
import com.omertex.mvprx.R
import com.omertex.mvprx.databinding.ActivityDetailsBinding
import com.omertex.test.app.data.model.MergeModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        val extras = intent.extras

        if (extras != null) {
            val data = extras.getParcelable<MergeModel>(MERGE_MODEL)

            data?.let {
                binding.ivImage.load(it.urls!!.thumb) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                    memoryCachePolicy(CachePolicy.ENABLED)
                }

                binding.tvName.text = it.name
                binding.tvEmail.text = it.email
                binding.tvWebsite.text = it.website
                binding.tvPhone.text = it.phone
                binding.tvCompanyName.text = it.company.companyName
                binding.tvCity.text = it.address.city
            }

        } else {
            Toast.makeText(this, " No data in bundle", Toast.LENGTH_LONG).show()
        }


    }

    companion object {
        private const val MERGE_MODEL = "mergeModel"
        fun getInstance(context: Context, data: MergeModel): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MERGE_MODEL, data)
            return intent
        }
    }
}