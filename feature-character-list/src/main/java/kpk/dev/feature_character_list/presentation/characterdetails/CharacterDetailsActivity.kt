package kpk.dev.feature_character_list.presentation.characterdetails

import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kpk.dev.feature_character_list.R
import kpk.dev.feature_character_list.presentation.base.BaseActivity
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import kpk.dev.presentation.ResourceState
import kpk.dev.presentation.loadImage
import javax.inject.Inject

class CharacterDetailsActivity : BaseActivity() {

    companion object {
        const val EXTRA_CHARACTER_ID = "char_id"
    }

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: CharacterDetailsViewModel

    private var snackBar: Snackbar? = null
    private val characterImage: ImageView by lazy {
        findViewById<ImageView>(R.id.iv_char_big_image)
    }

    private val tvName: TextView by lazy {
        findViewById<TextView>(R.id.tv_name)
    }

    private val tvNickname: TextView by lazy {
        findViewById<TextView>(R.id.tv_nickname)
    }

    private val tvBirthday: TextView by lazy {
        findViewById<TextView>(R.id.tv_birthday)
    }

    private val tvOccupation: TextView by lazy {
        findViewById<TextView>(R.id.tv_occupation)
    }

    private val tvStatus: TextView by lazy {
        findViewById<TextView>(R.id.tv_status)
    }

    private val tvAppearances: TextView by lazy {
        findViewById<TextView>(R.id.tv_appearances)
    }

    private val tvPortrayedBy: TextView by lazy {
        findViewById<TextView>(R.id.tv_portrayed)
    }

    override fun getLayoutId(): Int = R.layout.activity_character_details

    override fun init() {
        setToolBar(findViewById(R.id.toolbar))
        setTitle(getString(R.string.app_title))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel = vmFactory.get()
        progressBar = findViewById(R.id.pb_char_details)
        downloadData()
    }

    private fun downloadData() {
        val charId = intent.extras?.getInt(EXTRA_CHARACTER_ID) ?: -1
        if (charId == -1) {
            displayError(
                getString(R.string.invalid_char_id),
                getString(R.string.go_back)
            ) { onBackPressed() }
            return
        }
        viewModel.getCharacter(charId, false)
            .observe(this, Observer {
                when (it.resourceState) {
                    ResourceState.LOADING -> {
                        showProgress()
                    }
                    ResourceState.FAILURE -> {
                        hideProgress()
                        displayError(it.message, getString(R.string.try_again)) { downloadData() }
                    }
                    ResourceState.SUCCESS -> {
                        hideError()
                        hideProgress()
                        it.data?.let { charItem ->
                            characterImage.loadImage(charItem.img)
                            setTitle(charItem.name)
                            tvName.text = charItem.name
                            tvNickname.text =
                                String.format(getString(R.string.nickname), charItem.nickname)
                            tvBirthday.text =
                                String.format(getString(R.string.birthday), charItem.birthday)
                            tvOccupation.text = String.format(
                                getString(R.string.occupation),
                                charItem.occupation.joinToString()
                            )
                            tvStatus.text =
                                String.format(getString(R.string.status), charItem.status)
                            tvAppearances.text = String.format(
                                getString(R.string.appearances),
                                charItem.appearance.joinToString()
                            )
                            tvPortrayedBy.text =
                                String.format(getString(R.string.portrayed), charItem.portrayed)
                        }
                    }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}