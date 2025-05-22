package packt.app.MainConfig.modules;



import java.time.LocalDate;

public class Module_Produtos {
    private String idProdut;
    private String tipoEquipamento;
    private String marca;
    private String modelo;
    private Integer quantia;
    private LocalDate dataEntrada;
    private String Obs;

    //Contrutor
    public Module_Produtos(String idProdut, String tipoEquipamento, String marca, String modelo, Integer quantia, LocalDate dataEntrada, String obs) {
        this.idProdut = idProdut;
        this.tipoEquipamento = tipoEquipamento;
        this.marca = marca;
        this.modelo = modelo;
        this.quantia = quantia;
        this.dataEntrada = dataEntrada;
        Obs = obs;
    }


    //getter and setter
    public String getIdProdut() {
        return idProdut;
    }

    public void setIdProdut(String idProdut) {
        this.idProdut = idProdut;
    }

    public String getTipoEquipamentoProduto() {
        return tipoEquipamento;
    }

    public void setTipoEquipamentoProduto(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getMarcaProduto() {
        return marca;
    }

    public void setMarcaProduto(String marca) {
        this.marca = marca;
    }

    public String getModeloProduto() {
        return modelo;
    }

    public void setModeloProduto(String modelo) {
        this.modelo = modelo;
    }

    public Integer getQuantia() {
        return quantia;
    }

    public void setQuantiaProduto(Integer quantia) {
        this.quantia = quantia;
    }

    public LocalDate getDataEntradaProduto() {
        return dataEntrada;
    }

    public void setDataEntradaProduto(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getObsProduto() {
        return Obs;
    }

    public void setObsProduto(String obs) {
        Obs = obs;
    }
}