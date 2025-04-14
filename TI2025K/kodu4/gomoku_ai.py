#!/usr/bin/env python
# coding: utf-8

# LTAT.01.003 Tehisintellekt (20235kevad)
# Koduülesanne 4. Gomoku

# Fail, mida võib muuta ja mis tuleb esitada koduse töö lahendusena
# Faili nime peaks jätma samaks
# Faili võib muuta suvaliselt, kuid see peab sisaldama funktsiooni getTurn(),
# millele antakse argumendina ette mängijat tähistav number (1 - must ehk X; 2 - valge ehk O) 
# ning mis tagastab selle mängija järgmise käigu koordinaatide tuple'ina (rida, veerg)

import gomoku_baas as gb
import gomoku as gm


def getTurn(board, player):
    moves = gm.getPossMoves(gm.board)


    bestMove = None
    bestRate = 0
    for move in moves:
        rating = rateMove(move, player)
        if rating == -1:
            return move

        if (rating > bestRate):
            bestMove = move
            bestRate = rating
            #print(bestMove, bestRate)

    return bestMove


    # # Üks võimalikest käikudest
    # move = (7, 7)
    # # move = [7,7] #sobib ka, kusagil otseselt tuple'iks olekut ei kontrollita
    # # Siia tuleb järgmise käigu genereerimise loogika, kasutada võib ka siia faili loodud funktsioone
    # return move


def rateMove(move, player):
    dirPairs = [((-1, 0), (1, 0)), ((0, -1), (0, 1)), ((-1, 1), (1, -1)), ((1, 1), (-1, -1))]

    score = 0
    for pair in dirPairs:
        ownCount = 0
        enemyCount = 0
        for dir in pair:
            pos = [move[0], move[1]]
            pos[0] += dir[0]
            pos[1] += dir[1]
            #print(pos[0], pos[1], move[0], move[1])
            while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == player):
                ownCount += 1
                #print('leidis')

                pos[0] += dir[0]
                pos[1] += dir[1]


            pos = [move[0], move[1]]
            pos[0] += dir[0]
            pos[1] += dir[1]
            #print(pos[0], pos[1], move[0], move[1])
            while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == 3 - player):
                enemyCount += 1
                #print('leidis')

                pos[0] += dir[0]
                pos[1] += dir[1]

        score += ownCount
        if enemyCount >= 3:
            return -1

        if ownCount == 5:
            return -1
        

    return score