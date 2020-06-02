package com.djevtic.myswifttestcode.presentation.comparison

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.BaseActivity
import com.djevtic.myswifttestcode.MainActivity
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.presentation.comparison.adapter.PlayerComparisonAdapter
import com.djevtic.myswifttestcode.presentation.comparison.adapter.PlayerComparisonAdapter.Companion.DATA
import com.djevtic.myswifttestcode.presentation.comparison.adapter.PlayerComparisonAdapter.Companion.WIN
import com.djevtic.myswifttestcode.presentation.comparison.adapter.models.PlayerData
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.ATTACKER
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.DEFENDER
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.MIDFIELDER
import com.djevtic.myswifttestcode.utils.Serializer
import com.djevtic.myswifttestcode.utils.TimeUtils
import com.djevtic.myswifttestcode.utils.TypeFactory
import kotlinx.android.synthetic.main.activity_team_comparison_layout.*

class PlayerComparisonActivity : BaseActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_comparison_layout)
        prepareData()
    }

    private fun prepareData() {
        val player1DataString = intent.getStringExtra(MainActivity.PLAYER)
        val player2DataString = intent.getStringExtra(MainActivity.PLAYER2)
        if (player1DataString != null && player2DataString != null) {
            //Deserialized string to JSON object
            val player1 =
                Serializer.deserialize<Player>(player1DataString, TypeFactory.ObjectType.PLAYER)
            val player2 =
                Serializer.deserialize<Player>(player2DataString, TypeFactory.ObjectType.PLAYER)

            viewAdapter = PlayerComparisonAdapter()
            (viewAdapter as PlayerComparisonAdapter).setDataSet(
                prepareComparisonList(
                    player1,
                    player2
                )
            )
            comparisonRecycleView.apply {
                setHasFixedSize(true)
                viewManager = LinearLayoutManager(context)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }
    }

    /**
     * Prepare list which will be feed to adapter
     */
    private fun prepareComparisonList(player1: Player, player2: Player): List<PlayerData> {
        val playerDataList = mutableListOf<PlayerData>()

        playerDataList.add(prepareSemaphoreData(player1, player2))

        playerDataList.add(
            PlayerData(
                getString(R.string.name_comparison),
                "${player1.firstname} ${player1.lastname}",
                "${player2.firstname} ${player2.lastname}",
                playerOne = false,
                playerTwo = false,
                itemType = DATA
            )
        )
        playerDataList.add(
            PlayerData(
                getString(R.string.position_comparison),
                player1.position,
                player2.position,
                playerOne = false,
                playerTwo = false,
                itemType = DATA
            )
        )
        playerDataList.add(
            PlayerData(
                getString(R.string.years_comparison),
                TimeUtils.countAge(player1.birthDate),
                TimeUtils.countAge(player2.birthDate),
                playerOne = false,
                playerTwo = false,
                itemType = DATA
            )
        )
        playerDataList.add(
            PlayerData(
                getString(R.string.games_played_comparison),
                player1.games.appearencesGames.toString(),
                player2.games.appearencesGames.toString(),
                playerOne = false,
                playerTwo = false,
                itemType = DATA
            )
        )
        playerDataList.add(
            PlayerData(
                getString(R.string.yellow_card_comparison),
                player1.cards.yellow.toString(),
                player2.cards.yellow.toString(),
                playerOne = false,
                playerTwo = false,
                itemType = DATA
            )
        )
        playerDataList.add(
            PlayerData(
                getString(R.string.red_card_comparison),
                player1.cards.red.toString(),
                player2.cards.red.toString(),
                playerOne = false,
                playerTwo = false,
                itemType = DATA
            )
        )

        if (player1.position == ATTACKER || player1.position == MIDFIELDER) {
            playerDataList.add(
                PlayerData(
                    getString(R.string.goals_comparison),
                    player1.goals.totalGoals.toString(),
                    player2.goals.totalGoals.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.assistans_comparison),
                    player1.goals.assistsGoals.toString(),
                    player2.goals.assistsGoals.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.drible_success_comparison),
                    player1.dribbles.successDribbles.toString(),
                    player2.dribbles.successDribbles.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
        }

        if (player1.position == ATTACKER) {
            playerDataList.add(
                PlayerData(
                    getString(R.string.shot_total_comparison),
                    player1.shots.totalShots.toString(),
                    player2.shots.totalShots.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.shot_on_comparison),
                    player1.shots.onShots.toString(),
                    player2.shots.onShots.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.dribble_total_comparison),
                    (player1.dribbles.attemptsDribbles + player1.dribbles.successDribbles).toString(),
                    (player2.dribbles.attemptsDribbles + player2.dribbles.successDribbles).toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
        }

        if (player1.position == MIDFIELDER) {
            playerDataList.add(
                PlayerData(
                    getString(R.string.passes_total_comparison),
                    player1.passes.totalPasses.toString(),
                    player2.passes.totalPasses.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.passes_key_comparison),
                    player1.passes.keyPasses.toString(),
                    player2.passes.keyPasses.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.dribble_attemts_comparison),
                    player1.dribbles.attemptsDribbles.toString(),
                    player2.dribbles.attemptsDribbles.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
        }

        if (player1.position == DEFENDER) {
            playerDataList.add(
                PlayerData(
                    getString(R.string.tackles_total_comparison),
                    player1.tackles.totalTackles.toString(),
                    player2.tackles.totalTackles.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.blocks_comparison),
                    player1.tackles.blocksTackles.toString(),
                    player2.tackles.blocksTackles.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.interception_comparison),
                    player1.tackles.interceptionsTackles.toString(),
                    player2.tackles.interceptionsTackles.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.duel_total_comparison),
                    player1.duels.totalDuels.toString(),
                    player2.duels.totalDuels.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
            playerDataList.add(
                PlayerData(
                    getString(R.string.duel_won_comparison),
                    player1.duels.wonDuels.toString(),
                    player2.duels.wonDuels.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
        }

        if (player1.position == MIDFIELDER || player1.position == DEFENDER) {
            playerDataList.add(
                PlayerData(
                    getString(R.string.foul_commited_comparison),
                    player1.fouls.committedFouls.toString(),
                    player2.fouls.committedFouls.toString(),
                    playerOne = false,
                    playerTwo = false,
                    itemType = DATA
                )
            )
        }

        return playerDataList
    }

    private fun prepareSemaphoreData(player1: Player, player2: Player): PlayerData {
        val pointsPlayer1 = getPlayerPoints(player1)
        val pointsPlayer2 = getPlayerPoints(player2)
        return if (pointsPlayer1 > pointsPlayer2) {
            PlayerData("", "", "", playerOne = true, playerTwo = false, itemType = WIN)
        } else {
            PlayerData("", "", "", playerOne = false, playerTwo = true, itemType = WIN)
        }
    }

    /**
     * Count number of points for provided player based on requirements
     */
    private fun getPlayerPoints(player: Player): Double {
        var points = 0.0
        if (player.position == ATTACKER) {
            points += player.goals.totalGoals * 5
            points += player.goals.assistsGoals * 3
            points += player.shots.onShots * 0.5
            points += player.dribbles.successDribbles * 0.5

            points -= (player.shots.totalShots - player.shots.onShots) * 0.2
            points -= (player.dribbles.attemptsDribbles - player.dribbles.successDribbles) * 0.2
        }
        if (player.position == MIDFIELDER) {
            points += player.goals.assistsGoals * 5
            points += player.goals.totalGoals * 3
            points += player.passes.keyPasses
            points += player.dribbles.successDribbles * 0.5

            points -= (player.dribbles.attemptsDribbles - player.dribbles.successDribbles) * 0.5
            points -= player.fouls.committedFouls * 0.2
        }

        if (player.position == MIDFIELDER || player.position == ATTACKER) {
            points -= player.cards.red * 1
            points -= player.cards.yellow * 0.5
        }
        if (player.position == DEFENDER) {
            points += player.duels.wonDuels * 5
            points += player.tackles.blocksTackles * 3
            points += player.tackles.interceptionsTackles * 3
            points += player.tackles.totalTackles

            points -= player.cards.red
            points -= player.cards.yellow * 0.5
            points -= (player.duels.totalDuels - player.duels.wonDuels)
        }
        return points
    }


}