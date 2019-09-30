package com.example.test.contract

import com.example.contract.VotingContract
import com.example.state.VotingrState
import net.corda.core.identity.CordaX500Name
import net.corda.testing.core.TestIdentity
import net.corda.testing.node.MockServices
import net.corda.testing.node.ledger
import org.junit.Test


class VotingContractTests {
    private val ledgerServices = MockServices(listOf("com.example.contract", "com.example.flow"))
    private val megaCorp = TestIdentity(CordaX500Name("MegaCorp", "London", "GB"))

    private val candidateName: String = "Mahesh Salunkhe"
    private val votes: Int = 1

    private val signers = listOf(megaCorp.publicKey)



    @Test
    fun `transaction must include Create command`() {
        ledgerServices.ledger {
            transaction {
                output(VotingContract.ID, VotingrState(megaCorp.party, candidateName, votes))
                fails()
                command(signers, VotingContract.Commands.CastVote())
                verifies()
            }
        }
    }


}