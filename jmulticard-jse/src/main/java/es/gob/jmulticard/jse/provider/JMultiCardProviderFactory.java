package es.gob.jmulticard.jse.provider;

import java.security.Provider;
import java.util.logging.Logger;

import es.gob.jmulticard.apdu.connection.ApduConnection;
import es.gob.jmulticard.apdu.connection.ApduConnectionException;
import es.gob.jmulticard.card.Atr;
import es.gob.jmulticard.jse.provider.ceres.CeresProvider;
import es.gob.jmulticard.jse.provider.gide.SmartCafeProvider;
import es.gob.jmulticard.jse.smartcardio.SmartcardIoConnection;

/** Factori&iacute;a de proveedores para todas las tarjetas soportadas.
 * @author Tom&aacute;s Garc&iacute;a-Mer&aacute;s. */
public final class JMultiCardProviderFactory {

	private static final Logger LOGGER = Logger.getLogger("es.gob.jmulticard"); //$NON-NLS-1$

	// **************************************************************************
	// ********* ATR DNIe Y COMPATIBLES *****************************************

	private static final byte[] DNI_NFC_ATR_MASK = new byte[] {
		(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
		(byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x00
	};
	private static final Atr DNI_NFC_ATR = new Atr(new byte[] {
		(byte) 0x3B, (byte) 0x88, (byte) 0x80, (byte) 0x01, (byte) 0xE1, (byte) 0xF3, (byte) 0x5E, (byte) 0x11,
		(byte) 0x77, (byte) 0x81, (byte) 0xA1, (byte) 0x00, (byte) 0x03
	}, DNI_NFC_ATR_MASK);

	private static final byte[] DNI_ATR_MASK = new byte[] {
		(byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
		(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF
	};
	private static final Atr DNI_ATR = new Atr(new byte[] {
		(byte) 0x3B, (byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x6A, (byte) 0x44,
		(byte) 0x4E, (byte) 0x49, (byte) 0x65, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x90, (byte) 0x00
	}, DNI_ATR_MASK);
	private static final Atr TIF_ATR = new Atr(new byte[] {
		(byte) 0x3B, (byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x6A, (byte) 0x54,
		(byte) 0x49, (byte) 0x46, (byte) 0x31, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x90, (byte) 0x00
	}, DNI_ATR_MASK);
	private static final Atr FNMT_TC_430_ATR = new Atr(new byte[] {
		(byte) 0x3B, (byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x6A, (byte) 0x46,
		(byte) 0x4E, (byte) 0x4D, (byte) 0x54, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x90, (byte) 0x00
	}, DNI_ATR_MASK);

	// ********* FIN ATR DNIe Y COMPATIBLES *************************************
	// **************************************************************************

	// **************************************************************************
	// ********************* ATR FNMT-CERES *************************************

	private static final byte[] CERES_TC_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff
	};
	private static final Atr CERES_TC_ATR = new Atr(new byte[] {
        (byte) 0x3B, (byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x6A, (byte) 0x46,
        (byte) 0x4E, (byte) 0x4d, (byte) 0x54, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x03, (byte) 0x90, (byte) 0x00
    }, CERES_TC_ATR_MASK);


	private static final byte[] CERES_ST_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00,
		(byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff
	};
	private static final Atr CERES_ST_ATR = new Atr(new byte[] {
        (byte) 0x3B, (byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x6A, (byte) 0x43,
        (byte) 0x45, (byte) 0x52, (byte) 0x45, (byte) 0x53, (byte) 0x02, (byte) 0x2c, (byte) 0x34, (byte) 0x00,
        (byte) 0x00, (byte) 0x03, (byte) 0x90, (byte) 0x00
    }, CERES_ST_ATR_MASK);


	private static final byte[] CERES_SLE_FN20_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff
	};
	private static final Atr CERES_SLE_FN20_ATR = new Atr(new byte[] {
        (byte) 0x3B, (byte) 0xeF, (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x14, (byte) 0x80, (byte) 0x25,
        (byte) 0x43, (byte) 0x45, (byte) 0x52, (byte) 0x45, (byte) 0x53, (byte) 0x57, (byte) 0x05, (byte) 0x60,
        (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x90, (byte) 0x00
    }, CERES_SLE_FN20_ATR_MASK);


	private static final byte[] CERES_SLE_FN19_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff
	};
	private static final Atr CERES_SLE_FN19_ATR = new Atr(new byte[] {
        (byte) 0x3B, (byte) 0xeF, (byte) 0x00, (byte) 0x00, (byte) 0x40, (byte) 0x14, (byte) 0x80, (byte) 0x25,
        (byte) 0x43, (byte) 0x45, (byte) 0x52, (byte) 0x45, (byte) 0x53, (byte) 0x57, (byte) 0x01, (byte) 0x16,
        (byte) 0x01, (byte) 0x01, (byte) 0x03, (byte) 0x90, (byte) 0x00
    }, CERES_SLE_FN19_ATR_MASK);

	// ********************* FIN ATR FNMT-CERES *********************************
	// **************************************************************************

	// **************************************************************************
	// ********************* ATR G&D SMARTCAFE **********************************

	private static final byte[] GIDE_SCAF_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xf
	};

	/** ATR de tarjeta G&amp;D SmartCafe 3&#46;2. */
	private static final Atr GIDE_SCAF_ATR = new Atr(new byte[] {
		(byte) 0x3b, (byte) 0xf7, (byte) 0x18, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x31, (byte) 0xfe,
		(byte) 0x45, (byte) 0x73, (byte) 0x66, (byte) 0x74, (byte) 0x65, (byte) 0x2d, (byte) 0x6e, (byte) 0x66,
		(byte) 0xc4
	}, GIDE_SCAF_ATR_MASK);

	private static final byte[] GIDE_SCAF_MSC_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff
	};

	/** ATR de tarjeta MicroSD G&amp;D Mobile Security Card. */
	private static final Atr GIDE_SCAF_MSC_ATR = new Atr(new byte[] {
		(byte) 0x3b, (byte) 0x80, (byte) 0x80, (byte) 0x01, (byte) 0x01
	}, GIDE_SCAF_MSC_ATR_MASK);

	/** ATR de tarjeta G&amp;D SmartCafe 3&#46;2 con T=CL (v&iacute;a inal&aacute;mbrica). */
	private static final byte[] GIDE_SCAF_TCL_ATR_MASK = new byte[] {
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
		(byte) 0xf
	};

	private static final Atr GIDE_SCAF_TCL_ATR = new Atr(new byte[] {
		(byte) 0x3b, (byte) 0xf7, (byte) 0x18, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x31, (byte) 0xfe,
		(byte) 0x45, (byte) 0x73, (byte) 0x66, (byte) 0x74, (byte) 0x65, (byte) 0x2d, (byte) 0x6e, (byte) 0x66,
		(byte) 0xc4
	}, GIDE_SCAF_TCL_ATR_MASK);

	// ********************* FIN ATR G&D SMARTCAFE ******************************
	// **************************************************************************

	private JMultiCardProviderFactory() {
		// No instanciable
	}

	/** Obtiene el proveedor (con la conexi&oacute;n por defecto) correspondiente
	 * a la primera tarjeta encontrada en el sistema.
	 * @return Proveedor (con la conexi&oacute;n por defecto) correspondiente
	 *         a la primera tarjeta encontrada insertada o <code>null</code> si
	 *         no hay ninguna insertada, no ha lector de tarjetas o no se
	 *         encuentra ninguna tarjeta soportada. */
	public static Provider getProvider() {
		final ApduConnection conn = new SmartcardIoConnection();
		final long[] terminals;
		try {
			terminals = conn.getTerminals(false);
		}
		catch (final ApduConnectionException e1) {
			LOGGER.warning(
				"No se ha podido obtener la lista de lectores de tarjetas del sistema: " + e1 //$NON-NLS-1$
			);
			return null;
		}
		byte[] atr = null;
		for (final long terminal : terminals) {
			conn.setTerminal((int) terminal);
			try {
				atr = conn.reset();
			}
			catch(final Exception e) {
				continue;
			}
		}
		return getProvider(atr);
	}

	/** Obtiene el proveedor (con la conexi&oacute;n por defecto) correspondiente
	 * a la tarjeta del ATR indicado.
	 * @param atr ATR de la tarjeta.
	 * @return Proveedor (con la conexi&oacute;n por defecto) correspondiente
	 *         a la tarjeta del ATR indicado o <code>null</code> si el ATR no
	 *         es de ninguna tarjeta soportada. */
	public static Provider getProvider(final byte[] atr) {
		if (atr == null) {
			return null;
		}
		if (isDni(atr)) {
			return new DnieProvider();
		}
		if (isCeres(atr)) {
			return new CeresProvider();
		}
		if (isGiDeSmartCafe(atr)) {
			return new SmartCafeProvider();
		}
		return null;
	}

	private static boolean isDni(final byte[] atr) {
		if (
			DNI_ATR.equals(atr) ||
			TIF_ATR.equals(atr) ||
			DNI_NFC_ATR.equals(atr)
		) {
			return true;
		}
		else if (FNMT_TC_430_ATR.equals(atr)) {
			if (atr[15] >= (byte) 0x04 && atr[16] >= (byte) 0x30) {
				return true;
			}
		}
		return false;
	}

	private static boolean isCeres(final byte[] atr) {
		if (
			CERES_TC_ATR.equals(atr)       ||
			CERES_ST_ATR.equals(atr)       ||
			CERES_SLE_FN20_ATR.equals(atr) ||
			CERES_SLE_FN19_ATR.equals(atr)
		) {
			return true;
		}
		return false;
	}

	private static boolean isGiDeSmartCafe(final byte[] atr) {
		if (
			GIDE_SCAF_ATR.equals(atr)     ||
			GIDE_SCAF_MSC_ATR.equals(atr) ||
			GIDE_SCAF_TCL_ATR.equals(atr)
		) {
			return true;
		}
		return false;
	}

}
