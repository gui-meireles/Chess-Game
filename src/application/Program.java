package application;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import boardgame.exception.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;
import chess.pieces.Queen;

import java.util.*;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();


        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                //criar um metodo que vai receber o source como param. e vai percorrer o tabuleiro e pegar o valor do source
//                ChessPiece[][] pieces;
//                int c = UI.printBoard([sc][sc]);
//                Position pos = promoted.getChessPosition().toPosition();
//                ChessPiece cp = new Queen(board, Color.WHITE);
                if (source != null) {
                    System.out.println(source.getColumn());
                    System.out.println(source.getRow());
//                    ChessPiece[][] cm = chessMatch.getPieces();
//                    ChessPiece[][] cm = new ChessPiece[source.getRow()][source.getColumn()];
//                    ChessPiece[source.getColumn()][source.getRow()]
//                    System.out.println(cm);
                }

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println("The piece selected is: " + chessMatch.getPieces());

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }
                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/H/R/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while (!type.equals("B") && !type.equals("H") && !type.equals("Q") && !type.equals("R")) {
                        System.out.print("Invalid value! Enter piece for promotion (B/H/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
