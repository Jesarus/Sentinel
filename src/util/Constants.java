/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import server.actors.User;

/**
 *
 * @author David
 *         Gabriel Mendes
 *         Gabriel David
 */
public class Constants {
    
    public static final String VERSION = "2.1.4";
    public static final String FONT = "Tahoma";
    
    public static final String WINDOW_CLOSE_MESSAGE[] = {"Voc� deseja mesmo sair?", "Aviso"};
    public static final String DEFAULT_SEARCH_MESSAGE = "Digite e-mail para pesquisar...";
    public static final String DEFAULT_FILE_TEXT = "Nenhum arquivo selecionado...";
    public static final String NEWPOST_TITLE_TEXT = "Insira t�tulo da sua mensagem aqui.";
    public static final String NEWPHOTO_COMMENT_TEXT = "Insira um coment�rio da sua foto se quiser...";
    public static final String NULL_TITLE = "O t�tulo est� vazio!";
    public static final String NULL_TEXT = "O texto est� vazio!";
    
    public static final String BTFRIENDS_TEXT = "Amigos";
    public static final String BTGROUPS_TEXT = "Groupos";
    
    //WHY??
    public static final User DEFAULT_USER = new User("Autor Exclu�do", "email", "password", -1, "00/00/0000");
}
