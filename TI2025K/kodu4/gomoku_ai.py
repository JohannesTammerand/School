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
import math
from copy import deepcopy
<<<<<<< HEAD
import random


def getTurn(board, player, depth=4):
    moves = gm.getPossMoves(board)
=======
import numpy as np


lastOppMove = (0, 0)
lastMove = (0, 0)
lastRate = 0
lastBoardState = [[0]*15 for i in range(15)]
#print(lastBoardState[2][2])
>>>>>>> 5e8a1478e414e8b133dbc1c77216a8455a674909

def getTurn(board, move, depth=2):
    global lastRate, lastBoardState, lastOppMove

<<<<<<< HEAD
    if len(moves) < depth:
        depth = len(moves)
    move, rate = minimax(board, depth, player, player, -math.inf, math.inf)
    return move


def rateMove(move, player):
    # dirPairs = [((-1, 0), (1, 0)), ((0, -1), (0, 1)), ((-1, 1), (1, -1)), ((1, 1), (-1, -1))]

    # score = 0
    # for pair in dirPairs:
    #     ownCount = 0
    #     enemyCount = 0
    #     for dir in pair:
    #         pos = [move[0], move[1]]
    #         pos[0] += dir[0]
    #         pos[1] += dir[1]
    #         #print(pos[0], pos[1], move[0], move[1])
    #         while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == player):
    #             ownCount += 1
    #             #print('leidis')

    #             pos[0] += dir[0]
    #             pos[1] += dir[1]


    #         pos = [move[0], move[1]]
    #         pos[0] += dir[0]
    #         pos[1] += dir[1]
    #         #print(pos[0], pos[1], move[0], move[1])
    #         while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == 3 - player):
    #             enemyCount += 1
    #             #print('leidis')

    #             pos[0] += dir[0]
    #             pos[1] += dir[1]

    #     score += ownCount
    #     if enemyCount >= 3:
    #         return -100

    #     if ownCount == 5:
    #         return 100
        

    # return score

    directions = [(-1, 0), (0, -1), (-1, 1), (1, 1)]
    score = 0
=======
    for i in range(15):
        for j in range(15):
            if lastBoardState[i][j] != move:
                lastMove = (i, j)
            if lastBoardState[i][j] != 3 - move:
                lastOppMove = (i, j)

    moves = getFilteredMoves(board)
    bestTurns = [moves[0]]
    bestRate = 0
    startRate = rateBoard(board, move, 0)
    for turn in moves:
        b = deepcopy(board)
        b[turn[0]][turn[1]] = move
        _turn, rate = minimax(b, startRate, depth, move, move, float('-inf'), float('inf'))
        #print(turn, rate)
        if rate > 1000000:
                print("Made move", turn, "score:", rate)
                return turn
        if rate > bestRate:
            bestTurns = [turn]
            bestRate = rate
        elif rate == bestRate:
            bestTurns.append(turn)

    madeTurn = random.choice(bestTurns)
    #print("Made move", madeTurn, "score:", bestRate)

    lastBoardState = board
    return madeTurn
>>>>>>> 5e8a1478e414e8b133dbc1c77216a8455a674909

    for dx, dy in directions:
        line = getLine(move, dx, dy, player)
        score += scorePattern(line, player)

<<<<<<< HEAD
    return score

def getLine(move, dx, dy, player):
    line = ''
    x, y = move

    for offset in range(-4, 5):
        i = x + offset * dx
        j = y + offset * dy
        if 0 <= i < 15 and 0 <= j < 15:
            val = gm.board[i][j]
            if val == player:
                line += 'X'
            elif val == 0:
                line += '_'
            else:
                line += 'O'
        else:
            line += 'O'
    return line

def scorePattern(line, player):
    scores = {
        'XXXXX': 100000,
        '_XXXX_': 10000,
        'XXXX_': 5000,
        '_XXXX': 5000,
        'XXX__': 500,
        '__XXX': 500,
        '_XXX_': 800,
        'XX__': 100,
        '__XX': 100,
        '_XX_': 150,
    }

    total = 0
    for pattern, value in scores.items():
        total += line.count(pattern) * value
    return total

def rateBoard(player, board):
    #dirPairs = [((-1, 0), (1, 0)), ((0, -1), (0, 1)), ((-1, 1), (1, -1)), ((1, 1), (-1, -1))]

    # score = 0
    # for tile in gm.getPossMoves(board):
    #     for pair in dirPairs:
    #         count = 0
    #         for dir in pair:
    #             pos = [tile[0] + dir[0], tile[1] + dir[1]]
    #             #print(pos[0], pos[1], move[0], move[1])
    #             while (pos[0] > 0 and pos[1] > 0 and pos[0] < 14 and pos[1] < 14 and gm.board[pos[0]][pos[1]] == player):
    #                 count += 1
    #                 #print('leidis')

    #                 pos[0] += dir[0]
    #                 pos[1] += dir[1]

    #         score += count

    #         if count == 5:
    #             return 100
=======
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
>>>>>>> 5e8a1478e414e8b133dbc1c77216a8455a674909
        
    score = 0
    for move in gm.getPossMoves(board):
        score += rateMove(move, player)
        score += rateMove(move, 3 - player)

    #print(score)
    return score

def rateBoard(board, move, currentRate):

<<<<<<< HEAD
def minimax(board, depth, player, move, alpha, beta):
    bestTurn = (-1, -1)
    bestRate = 0
    moves = gm.getPossMoves(board)
    depth -= 1
    if depth >= 0 and len(moves) > depth:
        if player == move:
            bestTurn, bestRate = maximizer(board, moves, depth, player, alpha, beta)
        else:
            bestTurn, bestRate = minimizer(board, moves, depth, player, alpha, beta)
    return bestTurn, bestRate

def maximizer(board, moves, depth, player, alpha, beta):
    bestRate = 0
    bestTurns = [moves[0]]

    for move in moves:
        b = deepcopy(board)
        b[move[0]][move[1]] = player
        rate = rateBoard(player, b)
        if rate > bestRate:
            bestTurns = [move]
=======
    total = currentRate
    total += rateStone(lastMove, move, board)
    total -= rateStone(lastOppMove, move, board)

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

    if line.count('_XX_') > 0:
        print(pos)

    return line

def scorePattern(line):
    patterns = {
        'XXXXX': 1000000,
        '_XXXX_': 10000,
        'XXXX_': 5000, '_XXXX': 5000,
        '_XXX_': 1000, 'XXX__': 500, '__XXX': 500,
        '_XX_': 200, 'XX__': 100, '__XX': 100,
    }

    total = 0
    for pattern, score in patterns.items():
        total += line.count(pattern) * score
    return total

def getFilteredMoves(board, radius=1):
    moves = set()
    for i in range(15):
        for j in range(15):
            if board[i][j] != 0:
                for dx in range(-radius, radius + 1):
                    for dy in range(-radius, radius + 1):
                        ni, nj = i + dx, j + dy
                        if 0 <= ni < 15 and 0 <= nj < 15 and board[ni][nj] == 0:
                            moves.add((ni, nj))
    return list(moves)



def minimax(board, currentRate, depth, current_player, maximizing_player, alpha, beta):
    #print("minimax", depth)

    moves = getFilteredMoves(board, radius=2)
    if depth < 0 or not moves:
        return None, rateBoard(board, maximizing_player, currentRate)

    if current_player == maximizing_player:
        return maximizer(board, currentRate, depth, current_player, maximizing_player, alpha, beta)
    else:
        return minimizer(board, currentRate, depth, current_player, maximizing_player, alpha, beta)

def maximizer(board, currentRate, depth, current_player, maximizing_player, alpha, beta):
    #print("maximizer", depth)

    global lastMove

    bestRate = float('-inf')
    bestMoves = []
    moves = getFilteredMoves(board)

    for move in moves:
        b = deepcopy(board)
        b[move[0]][move[1]] = current_player
        lastMove = move
        _, rate = minimax(b, currentRate, depth - 1, 3 - current_player, maximizing_player, alpha, beta)
        if rate > bestRate:
>>>>>>> 5e8a1478e414e8b133dbc1c77216a8455a674909
            bestRate = rate
            bestMoves = [move]
        elif rate == bestRate:
<<<<<<< HEAD
            bestTurns.append(move)
        if depth > 0 and bestRate < 100:
                _turn, rate = minimax(b, depth - 1, 3 - player, 3-player, alpha, beta)
=======
            bestMoves.append(move)
>>>>>>> 5e8a1478e414e8b133dbc1c77216a8455a674909

        alpha = max(alpha, rate)
        if beta <= alpha:
            break

<<<<<<< HEAD
    return random.choice(bestTurns), bestRate

def minimizer(board, moves, depth, player, alpha, beta):
    bestTurns = [moves[0]]
    bestRate = 100

    for move in moves:
            b = deepcopy(board)
            b[move[0]][move[1]] = player
            rate = rateBoard(3-player, b)
            if rate == 0 and rate != bestRate:
                bestTurns=[move]
                bestRate = rate
            if depth > 0 and rate > 1:
                _turn, rate = minimax(b, depth - 1, player, player, alpha, beta)
            if rate < bestRate:
                bestRate = rate
                bestTurns = [move]
            elif rate == bestRate:
                bestTurns.append(move)

            beta = min(beta, rate)
            if beta <= alpha:
                break

    return random.choice(bestTurns), bestRate
=======
    return random.choice(bestMoves), bestRate

def minimizer(board, currentRate, depth, current_player, maximizing_player, alpha, beta):
    #print("minimizer", depth)

    global lastOppMove

    bestRate = float('inf')
    bestMoves = []
    moves = getFilteredMoves(board)

    for move in moves:
        b = deepcopy(board)
        b[move[0]][move[1]] = current_player
        lastOppMove = move
        _, rate = minimax(b, currentRate, depth - 1, 3 - current_player, maximizing_player, alpha, beta)
        if rate < bestRate:
            bestRate = rate
            bestMoves = [move]
        elif rate == bestRate:
            bestMoves.append(move)

        beta = min(beta, rate)
        if beta <= alpha:
            break

    return random.choice(bestMoves), bestRate
>>>>>>> 5e8a1478e414e8b133dbc1c77216a8455a674909
