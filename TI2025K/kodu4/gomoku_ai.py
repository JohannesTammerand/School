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
import numpy as np


lastOppMove = (0, 0)
lastMove = (0, 0)
lastRate = 0
lastBoardState = [[0]*15 for i in range(15)]

def getTurn(board, move, depth=2):
    global lastRate, lastBoardState, lastOppMove

    for row in board:
        for column in row:
            if column == move and lastBoardState[row][column] != move:
                lastMove = (row, column)
            if column == 3 - move and lastBoardState[row][column] != 3 - move:
                lastOppMove = (row, column)

    moves = getFilteredMoves(board)
    #bestTurns = [random.choice(moves)]
    bestTurns = [moves[0]]
    bestRate = 0
    startRate = rateBoard(board, move)
    for turn in moves:
        b = deepcopy(board)
        b[turn[0]][turn[1]] = move
        _turn, rate = minimax(b, startRate, lastMove, depth, move, move, float('-inf'), float('inf'))
        print(turn, rate)
        if rate > 1000000:
                print("Made move", turn, "score:", rate)
                return turn
        if rate > bestRate:
            bestTurns = [turn]
            bestRate = rate
        elif rate == bestRate:
            bestTurns.append(turn)

    madeTurn = random.choice(bestTurns)
    print("Made move", madeTurn, "score:", bestRate)

    lastBoardState = board
    return madeTurn


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

def rateBoard(board, move, lastMove, lastRate):

    total += rateStone(lastMove, move, board)
    total -= rateStone(lastOppMove, move, board)

    return total + lastRate

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
        return None, rateBoard(board, maximizing_player, lastMove, currentRate)

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
            bestRate = rate
            bestMoves = [move]
        elif rate == bestRate:
            bestMoves.append(move)

        alpha = max(alpha, rate)
        if beta <= alpha:
            break

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