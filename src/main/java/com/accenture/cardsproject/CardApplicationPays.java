package com.accenture.cardsproject;

import com.accenture.cardsproject.exceptions.InformationSendingException;
import com.accenture.cardsproject.exceptions.PrinterException;
import com.accenture.cardsproject.exceptions.ReportingPayException;
import com.accenture.cardsproject.exceptions.UpdatingBalanceException;
import com.accenture.cardsproject.persistence.models.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CardApplicationPays {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Random random = new Random();
    private final int MIN = 1;
    private final int MAX = 10;
    private final int RANDOM_EXCEPTION = 9;

    private void imprimirFactura() throws PrinterException {
        if ((random.nextInt(MAX - MIN + 1) + MIN) == RANDOM_EXCEPTION) {
            throw new PrinterException("Error al imprimir la factura. Papel Atascado");
        }
    }

    private void enviarInfoTC() throws InformationSendingException {
        if ((random.nextInt(MAX - MIN + 1) + MIN) == RANDOM_EXCEPTION) {
            throw new InformationSendingException("Error al enviar la informacion. Host caído.");
        }
    }

    private void informarPago() throws ReportingPayException {
        if ((random.nextInt(MAX - MIN + 1) + MIN) == RANDOM_EXCEPTION) {
            throw new ReportingPayException("Error al informar el pago. El sistema contable no responde y/o no atiende los pedidos.");
        }
    }

    private void actualizarSaldo() throws UpdatingBalanceException {
        if ((random.nextInt(MAX - MIN + 1) + MIN) == RANDOM_EXCEPTION) {
            throw new UpdatingBalanceException("Error al actualizar el saldo. La base de datos podría estar inaccesible.");
        }
    }

    public boolean cobrar(Card card, double importe) {
        if (card == null) {
            logger.error("Tarjeta inexistente");
            return false;
        }

        if (card.validateCard()) {
            if (importe <= 1000) {
                try {
                    imprimirFactura();
                    enviarInfoTC();
                    informarPago();
                    actualizarSaldo();

                    logger.info("Operación exitosa.");
                    return true;
                } catch (ReportingPayException | InformationSendingException | UpdatingBalanceException | PrinterException e) {
                    logger.info("Error al realizar el cobro. " + e.getMessage());
                }
            } else {
                logger.info("Importe inválido. Excede el límite permitido.");
            }
        } else {
            logger.info("Tarjeta inválida. Checkear la fecha de vencimiento.");
        }
        return false;
    }
}
