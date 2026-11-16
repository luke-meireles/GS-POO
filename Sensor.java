import java.util.List;

public interface Sensor {

    double lerValor();

    boolean estaFuncionando();

    String getTipo();

    String getUnidade();

    boolean passouDoLimite(double limite);

    List<Double> getLeituras();
}
