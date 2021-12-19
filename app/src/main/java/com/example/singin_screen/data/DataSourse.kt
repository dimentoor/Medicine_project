package com.example.singin_screen.data

import com.example.singin_screen.R
import com.example.singin_screen.model.Products

object DataSourse {

    val medicines = listOf(
        Products("Имя_1","pills_1", R.drawable.amiksin),
        Products("Имя_2","pills_2",R.drawable.anaferon),
        Products("Имя_3","pills_3",R.drawable.arbidol),
        Products("Имя_4","pills_4",R.drawable.ergoferon),
        Products("Имя_5","pills_5",R.drawable.ingaverin)

    )

    val biologicalactadd = listOf(
        Products("Имя_1","bio_act_add_1", R.drawable.bifiform),
        Products("Имя_2","bio_act_add_2",R.drawable.detrimax),
        Products("Имя_3","bio_act_add_3",R.drawable.maksilak),
        Products("Имя_4","bio_act_add_4",R.drawable.vetom),
        Products("Имя_5","bio_act_add_5",R.drawable.zn)

    )

    val disinfectants = listOf(
        Products("Имя_1","disinfectant_1", R.drawable.furacilin),
        Products("Имя_2","disinfectant_2",R.drawable.iiod),
        Products("Имя_3","disinfectant_3",R.drawable.marla),
        Products("Имя_4","disinfectant_4",R.drawable.weps),
        Products("Имя_5","disinfectant_5",R.drawable.perekis)

    )
}