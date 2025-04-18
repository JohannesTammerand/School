#!/usr/bin/env python
# coding: utf-8

# LTAT.01.003 Tehisintellekt (20235kevad)
# Koduülesanne 4. Gomoku

# Fail, mida võib muuta ja mis tuleb esitada koduse töö lahendusena
# Faili nime peaks jätma samaks
# Faili võib muuta suvaliselt, kuid see peab sisaldama funktsiooni getTurn(),
# millele antakse argumendina ette mängijat tähistav number (1 - must ehk X; 2 - valge ehk O) 
# ning mis tagastab selle mängija järgmise käigu koordinaatide tuple'ina (rida, veerg)

import random
import gomoku_baas as gb
import gomoku as gm
from copy import deepcopy


player = 1

def getTurn(board, move, depth=2):
    print("getTurn")
    global player
    player = move

    moves = gm.getPossMoves(board)
    bestTurns = random.choice(moves)
    bestRate = 0
    for turn in moves:
        b = deepcopy(board)
        b[turn[0]][turn[1]] = player
        _turn, rate = minimax(b, depth, move, float('-inf'), float('inf'))
        if rate > bestRate:
            bestTurns = [turn]
            bestRate = rate
        elif rate == bestRate:
            bestTurns.append(turn)

    return random.choice(bestTurns)


    # moves = gm.getPossMoves(gm.board)


    # bestMove = None
    # bestRate = 0
    # for move in moves:
    #     rating = rateMove(move, player)
    #     if rating == 100:
    #         return move

    #     if (rating > bestRate):
    #         bestMove = move
    #         bestRate = rating
    #         #print(bestMove, bestRate)

    # return bestMove


    # # Üks võimalikest käikudest
    # move = (7, 7)
    # # move = [7,7] #sobib ka, kusagil otseselt tuple'iks olekut ei kontrollita
    # # Siia tuleb järgmise käigu genereerimise loogika, kasutada võib ka siia faili loodud funktsioone
    # return move


def rateMove(turn, move):
    #print("rateMove")
    dirPairs = [((-1, 0), (1, 0)), ((0, -1), (0, 1)), ((-1, 1), (1, -1)), ((1, 1), (-1, -1))]

    score = 0
    for pair in dirPairs:
        ownCount = 0
        enemyCount = 0
        for dir in pair:
            pos = [turn[0], turn[1]]
            pos[0] += dir[0]
            pos[1] += dir[1]
            #print(pos[0], pos[1], move[0], move[1])
            while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == move):
                ownCount += 1
                #print('leidis')

                pos[0] += dir[0]
                pos[1] += dir[1]


            pos = [turn[0], turn[1]]
            pos[0] += dir[0]
            pos[1] += dir[1]
            #print(pos[0], pos[1], move[0], move[1])
            while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == 3 - move):
                enemyCount += 1
                #print('leidis')

                pos[0] += dir[0]
                pos[1] += dir[1]

        score += ownCount
        if enemyCount >= 3:
            return 100

        if ownCount == 5:
            return 100
        

    return score

def rateBoard(board, move):
    total = 0
    for i in range(15):
        for j in range(15):
            if board[i][j] == move:
                total += rateStone((i, j), move, board)
            elif board[i][j] == 3 - player:
                total -= rateStone((i, j), 3 - move, board)
    return total


def rateStone(pos, move, board):
    directions = [(-1, 0), (0, -1), (-1, 1), (1, 1)]
    score = 0
    for dx, dy in directions:
        line = getLineFromBoard(pos, dx, dy, board, move)
        score += scorePattern(line)
    return score

def getLineFromBoard(pos, dx, dy, board, move):
    line = ''
    x, y = pos

    for offset in range(-4, 5):
        i = x + offset * dx
        j = y + offset * dy
        if 0 <= i < 15 and 0 <= j < 15:
            val = board[i][j]
            if val == move:
                line += 'X'
            elif val == 0:
                line += '_'
            else:
                line += 'O'
        else:
            line += 'O'
    return line

def scorePattern(line):
    patterns = {
        'XXXXX': 100000,
        '_XXXX_': 10000,
        'XXXX_': 5000, '_XXXX': 5000,
        '_XXX_': 1000, 'XXX__': 500, '__XXX': 500,
        '_XX_': 200, 'XX__': 100, '__XX': 100
    }

    total = 0
    for pattern, score in patterns.items():
        total += line.count(pattern) * score
    return total



def minimax(board, depth, move, alpha, beta):
    print("minimax")
    moves = gm.getPossMoves(board)
    bestTurn = moves[0]
    bestRate = 0
    
    depth -= 1
    if depth >= 0 and len(moves) > depth:
        if player == move:
            bestTurn, bestRate = maximizer(board, moves, depth, move, alpha, beta)
        else:
            bestTurn, bestRate = minimizer(board, moves, depth, move, alpha, beta)
    return bestTurn, bestRate
    

def maximizer(board, allturns, depth, move, alpha, beta):
    print("maximizer")
    bestturn = (-1, -1)
    bestturns = allturns
    bestrate = 100
    for turn in allturns:
        b = deepcopy(board)
        b[turn[0]][turn[1]] = move
        rate = rateBoard(b, move)
        if rate==100 and bestrate<100:
            bestturns = [turn]
            bestrate = rate
        if depth > 0 and bestrate<100:
            _turn, rate = minimax(b, depth, 3 - move, alpha, beta)
        if rate > bestrate:
            bestrate = rate
            bestturns = [turn]
        elif rate == bestrate:
            bestturns.append(turn)

        alpha = max(alpha, rate)
        if beta <= alpha:
            break

    bestturn = random.choice(bestturns)
    return bestturn, bestrate

def minimizer(board, allturns, depth, move, alpha, beta):
    print("minimizer")
    bestRate = 100
    bestTurns = allturns

    for turn in allturns:
        b = deepcopy(board)
        b[turn[0]][turn[1]] = move
        if depth > 0:
            _turn, rate = minimax(b, depth, 3 - move, alpha, beta)
        else:
            rate = 60 - rateBoard(b, move)
        if rate < bestRate:
            bestRate = rate
            bestTurns = [turn]
        elif rate == bestRate:
            bestTurns.append(turn)

        beta = min(beta, rate)
        if beta <= alpha:
            break

    return random.choice(bestTurns), bestRate