package model.bean;

public class CadAviso {
    private int id;
    private String titulo;
    private String conteudo;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public String getTitulo() {

        return titulo;

    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }

    public String getConteudo() {

        return conteudo;

    }

    public void setConteudo(String conteudo) {

        this.conteudo = conteudo;

    }

    
}
