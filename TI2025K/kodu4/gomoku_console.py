#!/usr/bin/env python
# coding: utf-8

# LTAT.01.003 Tehisintellekt (2025 kevad)
# Koduülesanne 4. Gomoku
# 
# Fail, mis mängib, sisuliselt on tegu ühe klassiga, mis on klassiks vormistamata.
# Kutsub välja tehisintellekti käiguvaliku failist gomoku_ai.py ja võtab muud mängimiseks vajaminevad funktsioonid failist gomoku_baas.
# 
# Kõiki funktsioone võib siin vastavalt oma suvale muuta, kodutöös esitada tuleb hoopis enda **gomoku_ai.py** fail. Võite eeldada, et kontrollimisel kasutatakse analoogseid väljakutseid.

# ## Impordid
import importlib
import time
from copy import deepcopy
import numpy as np
import random

# Näe, tehisintellekt (failist gomoku_ai.py):
import gomoku_ai
importlib.reload(gomoku_ai) #Loe fail värskelt sisse - puhuks, kui oled seal midagi muutnud
# Esialgu arenduse käigus võite teha ka siia notebooki mõne funktsiooni või klassi,
# aga enne kodutöö esitamist katsetage kindlasti ka välise failiga.

# Üldised gomokureeglid, lauaväljatrükk jms.
import gomoku_baas as gb
importlib.reload(gb)


# Muutujate algväärtustamine ========================================================================================

# Mängulaua suurus
boardSize = 15
# Mängijad
players = [".","X","O"]


# ## Abifunktsioonid: mängulaua algväärtustamine
# Algus
def initialize(swap = None, printBoard=True, valge = gomoku_ai):
    # swap - kellena jätkata (1-must, 2-valge)
    # printBoard - kas joonistada mänguväli
    # valge - kes mängib valge eest 
    
    # Tagastab 
    # player - kes järgmisena käib
    # AIPlayer - kes on tehisintellekt (relevantne, kui playAI on True)
    # board - algväärtustatud mängulaud
    
    global boardSize
    # Käiku tegev mängija - alustab 1 ehk must ehk X 
    player = 1
    # Kumb mängija on tehisintellekt - praegu 2 ehk valge ehk 0
    AIPlayer = 2
    
    # Mängulaud
    board = [np.zeros(boardSize,dtype=int) for i in range(boardSize)]
    # 1. must nupp
    move = gb.getTurnRandom(board)
    board[move[0]][move[1]] = 1
    # 1. valge nupp
    move = gb.getTurnRandom(board)
    board[move[0]][move[1]] = 2
    # 2. must nupp
    move = gb.getTurnRandom(board)
    board[move[0]][move[1]] = 1
    # Joonista mängulaud, lase pool valida
    if printBoard:
        gb.drawBoard(board)
    # Kui tegu on automaatmänguga, siis võta Swap vastus muutujast
    choice = swap
    # Vastasel juhul saad selle inimeselt
    if not choice:
        choice = input("Kumba mängijana jätkad? 1 = must (X), 2 = valge (O): ")
        choice = int(choice)
        while choice not in [1,2]:
            choice = input("Sisesta täisarv (1 või 2).\nKumba mängijana jätkad? 1 = must (X), 2 = valge (O): ")
            choice = int(choice)
    if choice == 1:
        AIPlayer = 2
        #############################################################
        # Testimise jaoks võib esialgu kasutada juhuslikku käiku (kommenteeri vastav rida välja)
        if valge=="random":
            move = gb.getTurnRandom(board)
        else:
            move = valge.getTurn(deepcopy(board),AIPlayer)
        board[move[0]][move[1]] = 2
        # Järgmisena käib nüüd must
        player = 1
        if printBoard:
            gb.drawBoard(board)
    else:
        # swap valik oli 2
        player = 2
        AIPlayer = 1
    return player, AIPlayer, board

# Mängimine ======================================================================================================

#Mängi käsitsi
def manual(playAI = True):
    # playAI - Tehisintellekti vastu mängimine (True või False)
    global players, boardSize
    # Algväärtustus, trükib ka mängulaua välja
    if playAI == False:
        # Kui mängib kaks inimest, siis teevad nad kõik käigud.
        player, AIPlayer, board = initialize(swap=2)
    else:
        # Mängivad inimene ja tehisintellekt
        #player, AIPlayer, board = initialize()
        player, AIPlayer, board = initialize() #Kui tahta juhuslikuga mängida
    while True:
        print("Käib", players[player])
        # Korratakse seni, kuni kasutaja sisestab oma käigu koordinaadid
        while True:
            if playAI and player == AIPlayer:
                # Arvuti käik
                #############################################################
                # Testimise jaoks võib esialgu kasutada juhuslikku käiku (kommenteeri vastav rida välja)
                move = gomoku_ai.getTurn(deepcopy(board), player)
                #move = gb.getTurnRandom(board)
            else:
                # Inimese käik
                move = input("Järgmine käik <rida> <veerg>: ")
                move = list(map(int, move.split()))
            if len(move)!=2 or not gb.legalMove(board, move):
                print(move,"on vale käik.")
                print("Sisesta kaks täisarvu tühikuga eraldatult. Laua suurus on",boardSize)
                continue
            print(move)
            break
        # Tee käik
        board[move[0]][move[1]] = player
        gb.drawBoard(board)
        #Mängija vahetub
        player = 3-player
        #Kas mäng on läbi?
        end, winner = gb.isEnd(board)
        if end:
            if winner == 0:
                print("Mäng lõppes viigiga.")
            else:
                print("Gomoku! Võitis " + players[winner] + ".")
            #Mängutsükkel läbi
            break


# Mängimine ======================================================================================================
# Automaatmäng
def play(must, valge, printBoard = False):
    # printBoard - kas trükkida laud välja
    # must - must mängija
    # valge - valge mängija
    global players, boardSize
    # Algväärtustus, trükib ka mängulaua välja
    # Kui SWAP, siis on järgmine käija must (inimene)
    player, AIPlayer, board = initialize(swap=random.choice([1,2]))
    while True:
        print("Käib", players[player])
        # Korratakse seni, kuni kasutaja sisestab oma käigu koordinaadid
        while True:
            if player == 1:
                move = must.getTurn(deepcopy(board), player)
                #move = gb.getTurnRandom(board)
            else:
                move = valge.getTurn(deepcopy(board), player)
                #move = gb.getTurnRandom(board)
            if len(move)!=2 or not gb.legalMove(board, move):
                print("Vale käik:",players[player],move)
                continue
            print(move)
            break
        # Tee käik
        board[move[0]][move[1]] = player
        if printBoard:
            gb.drawBoard(board)
        #Mängija vahetub
        player = 3-player
        #Kas mäng on läbi?
        end, winner = gb.isEnd(board)
        if end:
            if winner == 0:
                print("Mäng lõppes viigiga.")
            else:
                print("Gomoku! Võitis " + players[winner] + ".")
            #Mängutsükkel läbi
            break



# Mõned näited, kuidas see komplekt töötama peaks: ühe käigu küsimine konkreetsele lauaseisule ja käsitsi läbimäng.
# Testimiseks ======================================================================================================

board1 = [[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0],
 [0, 0, 2, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0],
 [0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
 [0, 2, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0],
 [0, 0, 0, 0, 0, 2, 1, 0, 0, 2, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]



#Tee üks käik sellel laual
test1 = False
if test1==True:
    gb.drawBoard(board1)
    algusaeg = time.time()
    move = gomoku_ai.getTurn(board1, 1)
    aeg = time.time()-algusaeg
    print("Käigu leidmiseks kulus",round(aeg),"sekundit.")
    print("Mustana (X):",move)
    if board1[move[0]][move[1]] ==0:
        board1[move[0]][move[1]] = 1
        gb.drawBoard(board1)
    else:
        print("Vale käik!")
    print()

test2 = False
if test2==True:
    move = gomoku_ai.getTurn(board1, 2)
    print("Valgena (O):",move)
    if board1[move[0]][move[1]] ==0:
        board1[move[0]][move[1]] = 2
        gb.drawBoard(board1)
    else:
        print("Vale käik!")
    print("-----------------------------------")



#Mängi üks mäng inimese vastu
manual()

# Lase oma TIl iseenda vastu mängida
#play(gomoku_ai, gomoku_ai, printBoard = True)





