package com.example.contract


import com.example.state.VotingrState
import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.contracts.requireSingleCommand
import net.corda.core.transactions.LedgerTransaction
import net.corda.core.contracts.requireThat


// ************
// * Contract *
// ************
class VotingContract : Contract {
    companion object {
        // Used to identify our contract when building a transaction.
        const val ID = "com.example.contract.VotingContract"
    }

    // Used to indicate the transaction's intent.
    interface Commands : CommandData {
        // Issue a new Vote.
        class CastVote : Commands
    }

    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
    // does not throw an exception.
    override fun verify(tx: LedgerTransaction) {

        // Verification logic goes here.
        val command = tx.commands.requireSingleCommand<Commands>().value
        when (command) {
            is Commands.CastVote -> {
                requireThat()
                {
                    "There should be no input state" using (tx.inputs.isEmpty())
                    "There should be one input state" using (tx.outputs.size == 1)
                    "The output state must be of type VotingState" using (tx.outputs.get(0).data is VotingrState)
                    val outputState = tx.outputs.get(0).data as VotingrState
                    "The candidate Name  must be greater than 8 characters long" using (outputState.candidateName.length > 8)
                }
            }
        }
    }

}