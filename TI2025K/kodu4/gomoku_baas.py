#!/usr/bin/env python
# coding: utf-8

# LTAT.01.003 Tehisintellekt (2025 kevad)
# Koduülesanne 4. Gomoku

# Üldised gomokulauajoonistamised ja muud kasulikud funktsioonid
# Fail, mida koduse töö esitamisel EI SAADETA

import gomoku_ai
import numpy as np
import random


# Muutujate algväärtustamine ========================================================================================

# Mängulaua suurus
boardSize = 15
# Mängijad
players = [".","X","O"] 
# Värvid aknas
playerColours = ["gray75","black","white"] 
# Väljatrükiks
plNames = ["..","must","valge"] 

# Funktsioonid: mängutehnilised ======================================================================================

# Kontrollib, kas sinna võib käia
def legalMove(bd, move):
    if move[0] not in range(0,boardSize) or move[1] not in range(0,boardSize):
        return False
    if bd[move[0]][move[1]]==0:
        return True
    return False

# Tagastab mängija nupu võimalike käikude listi 
# Kui võimalikud käigud puuduvad, tagastab tühja listi
def getPossMoves(bd):
    possMoves = []
    for rownr, row in enumerate(bd):
        for colnr, value in enumerate(row):
            if value==0:
            #if legalMove((row,col)):
                possMoves.append((rownr,colnr))
    return possMoves    

# Suvaline käik kuhugi vabasse ruutu
def getTurnRandom(bd):
    # list võimalike käikudega
    allmoves = getPossMoves(bd)
    return random.choice(allmoves)


# Kontrolli, kas reas on täpselt viis sama värvi nuppu järjest
# gomoku - 5 nupu järjend
# row - rida, milles seda otsitakse
def checkRow(gomoku, row):
    n = len(gomoku)
    # Eeldame, et kõik õiged väärtused
    #value = player
    value = gomoku[0]
    matches = [i for i in range(len(row)-n+1) if np.array_equal(gomoku,row[i:i+n])]
    # Viisik on olemas
    if len(matches)>0:
        # Kontrolli, ega tegu pole kuuikuga
        rokumoku = gomoku + [value]
        for match in matches:
            if (not np.array_equal(row[match-1:match+n],rokumoku)) \
			and (not np.array_equal(row[match:match+n+1],rokumoku)):
                return True
    return False
    
# Kontroll, kas mäng on lõppenud
def isEnd(bd):
    answer = False
    winner = 0
    # Raske uskuda, aga mängulaud on täis
    if len(getPossMoves(bd)) ==0:
        return True
    # Kellelgi on täpselt viis järjest ehk gomoku
    # Kontrolli seda mõlema mängija kohta
    # Eeldame, et mõlemad korraga ei saa gomokuni jõuda
    for pl in [1,2]:
        gomoku = [pl for i in range(5)]
        x = boardSize-len(gomoku)+1 #milliseid diagonaale kontrollida
        #read
        #veerud
        #langevad diagonaalid
        #tõusvad diagonaalid
        if any([checkRow(gomoku,row) for row in bd]) or \
        any([checkRow(gomoku,row) for row in np.transpose(bd)]) or \
        any([checkRow(gomoku,row) for row in [np.diagonal(bd,i) for i in range(-x,x)]]) or \
        any([checkRow(gomoku,row) for row in [np.diagonal(np.fliplr(bd),i) for i in range(-x,x)]]):
            answer = True
            winner = pl
    return answer, winner


# Funktsioonid: visuaal ========================================================================================
##---------------------------------------------------------------------------------------
# Käsurea mängulaua funktsioonid
# Mängulaua joonistamine     
def drawBoard(bd):
    global boardSize, players
    print(" ",end = "  ")
    for i in range(boardSize):
        if i<10:
            print(i, end = "  ")
        else:
            print(i, end=" ")
    print()
    for r, row in enumerate(bd):
        if r<10:
            print(r, end = "  ")
        else:
            print(r, end=" ")
        for value in row:
            print(players[value], end="  ")
        print("\n")

