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
import math
from copy import deepcopy
import random


def getTurn(board, player, depth=4):
    moves = gm.getPossMoves(board)


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

    for dx, dy in directions:
        line = getLine(move, dx, dy, player)
        score += scorePattern(line, player)

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
        
    score = 0
    for move in gm.getPossMoves(board):
        score += rateMove(move, player)
        score += rateMove(move, 3 - player)

    #print(score)
    return score


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
            bestRate = rate
        elif rate == bestRate:
            bestTurns.append(move)
        if depth > 0 and bestRate < 100:
                _turn, rate = minimax(b, depth - 1, 3 - player, 3-player, alpha, beta)

        alpha = max(alpha, rate)
        if beta <= alpha:
            break

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