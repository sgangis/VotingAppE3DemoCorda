package com.example.state

import com.example.contract.VotingContract
import net.corda.core.contracts.*
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party

// *********
// * State *
// *********
@BelongsToContract(VotingContract::class)
data class VotingrState(
        val owner: Party,
        val candidateName: String,
        val votes: Int,
        val linearId: UniqueIdentifier = UniqueIdentifier()) : ContractState {
    // Participants is a list of all the parties who should
    // be notified of the creation or consumption of this state.

    override val participants: List<AbstractParty> = listOf(owner )

}
