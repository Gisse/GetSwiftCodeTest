package com.djevtic.myswifttestcode.presentation.player

import android.os.Bundle
import com.djevtic.myswifttestcode.BaseActivity
import com.djevtic.myswifttestcode.MainActivity
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.utils.Serializer
import com.djevtic.myswifttestcode.utils.TypeFactory
import kotlinx.android.synthetic.main.activity_player_layout.*

class PlayerInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_layout)
        prepareData()
    }

    private fun prepareData() {
        val playerData = intent.getStringExtra(MainActivity.PLAYER)
        if(playerData != null){
            //Deserialized string to JSON object
            var player = Serializer.deserialize<Player>(playerData, TypeFactory.ObjectType.PLAYER)
            playerName.text = "${player.firstname} ${player.lastname}"
            playerPosition.text = player.position
            playerPlayed.text = player.games.appearencesGames.toString()
        }
    }
}