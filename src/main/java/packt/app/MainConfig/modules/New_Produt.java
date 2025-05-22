package packt.app.MainConfig.modules;


import packt.database.DB_NewProdut;

import java.time.LocalDate;

public class New_Produt {

    private String idProdut;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private int quantia;
    private LocalDate dataEntrada;
    private String obs;

    DB_NewProdut dbNewProdut = new DB_NewProdut();

    public New_Produt(String idProdut, String tipoEquipamento, String marca, String modelo, int quantia, LocalDate dataEntrada, String obs) {
        this.idProdut = idProdut;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.quantia = quantia;
        this.dataEntrada = dataEntrada;
        this.obs = obs;
    }

    // Getters e Setters
    public String getIdProdut() { return idProdut; }
    public void setIdProdut(String idProdut) { this.idProdut = idProdut; }

    public String getTipoEquipamento() { return tipoEquipamento; }
    public void setTipoEquipamento(String tipoEquipamento) { this.tipoEquipamento = tipoEquipamento; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getQuantia() { return quantia; }
    public void setQuantia(int quantia) { this.quantia = quantia; }

    public LocalDate getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }

    public String getObs() { return obs; }
    public void setObs(String obs) { this.obs = obs; }

    public void adicionarProduto(){
        dbNewProdut.create(this);
    }
}
