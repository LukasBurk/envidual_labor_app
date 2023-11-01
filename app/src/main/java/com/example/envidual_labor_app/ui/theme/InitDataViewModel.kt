package com.example.envidual_labor_app.ui.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.uhn.hl7v2.HL7Exception
import ca.uhn.hl7v2.parser.PipeParser
import kotlinx.coroutines.flow.StateFlow
import org.openehealth.ipf.modules.hl7.kotlin.asIterable
import org.openehealth.ipf.modules.hl7.kotlin.get
import org.openehealth.ipf.modules.hl7.kotlin.value
import java.text.SimpleDateFormat
import androidx.compose.ui.focus.FocusDirection.Companion.In
import ca.uhn.hl7v2.*
import ca.uhn.hl7v2.model.Message
import ca.uhn.hl7v2.model.Structure
import ca.uhn.hl7v2.parser.*
import com.example.envidual_labor_app.ui.theme.component.ValueCard
import org.openehealth.ipf.modules.hl7.kotlin.asIterable
import org.openehealth.ipf.modules.hl7.kotlin.get
import org.openehealth.ipf.modules.hl7.kotlin.value

class InitDataViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val patient_Name: StateFlow<String> = savedStateHandle.getStateFlow("patient_Name", "")
    val patient_DateOfBirth: StateFlow<String> = savedStateHandle.getStateFlow("patient_DateOfBirth", "")
    val patient_Tagebuchnummer: StateFlow<String> = savedStateHandle.getStateFlow("patient_Tagebuchnummer", "")
    val obxListfilled: StateFlow<List<Structure>> = savedStateHandle.getStateFlow("obxListfilled", mutableListOf())
    val filledMessage: StateFlow<String> = savedStateHandle.getStateFlow("patient_Tagebuchnummer", "")
    val befunde: StateFlow<Int> = savedStateHandle.getStateFlow("befunde", 0)

    // HL7 Datei
    val textform =
        "MSH|^~\\&|PROMED-OPEN|IMD Berlin MVZ|MCS|TESTX|20221123103004||ORU^R01|2211231030040555890287|P|2.3|||AL|AL|DE||DE|\r" +
                "PID|1||||Test^Jeannette HL7||19800101|O|||Test, Jeannette HL7^^^^^^^||||||||\r" +
                "PV1|1||||||Medizinisches Labor Potsdam^^^^^^DE||||||||||||||E|||E\r" +
                "ORC|NW||0555890287^IMD Berlin MVZ||A||||20221123103004||||||20221123095200|||||||||||||||||||||||||\r" +
                "OBR|1||0555890287^IMD Berlin MVZ||APN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|1|ST|6APN^Alkalische Phosphatase i.S.||!!Storno|µmol/s*l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|2||0555890287^IMD Berlin MVZ||APN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|2|CE|7AP^Alkalische Phosphatase i.S.(Photom.)||1.09|µmol/s*l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|3||0555890287^IMD Berlin MVZ||BILI|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|3|CE|7BILI^Bilirubin (gesamt) i.S.    (Photom.)||8.54|µmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|4||0555890287^IMD Berlin MVZ||CA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|4|ST|6CA^Calcium i.S.||!!Storno|mmol/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|5||0555890287^IMD Berlin MVZ||CA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|5|CE|7CA^Calcium i.S.               (Photom.)||2.29|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|6||0555890287^IMD Berlin MVZ||CL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|6|ST|6CL^Chlorid i.S.                   (ISE)||!!Storno|mmol/l|98 - 106||||D|||||Schreiber, Jeannette\r" +
                "OBR|7||0555890287^IMD Berlin MVZ||CL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|7|CE|7CL^Chlorid i.S.                   (ISE)||106|mmol/l|98 - 107||||P|||||Schreiber, Jeannette\r" +
                "OBR|8||0555890287^IMD Berlin MVZ||CHOL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|8|ST|6CHOL^Cholesterin i.S.||!!Storno|mmol/l|||||D|||||Schreiber, Jeannette\r" +
                "NTE|1||          \r" +
                "NTE|2||Bei TG-Werten > 4.56 mmol/l(> 400 mg/dl) erfolgt die\r" +
                "NTE|3||Berechnung von Non-HDL-Cholesterin.\r" +
                "OBR|9||0555890287^IMD Berlin MVZ||CHOL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|9|CE|7CHOL^Cholesterin i.S.           (Photom.)||5.60|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "NTE|4||          \r" +
                "NTE|5||Bei TG-Werten > 4.56 mmol/l(> 400 mg/dl) erfolgt die\r" +
                "NTE|6||Berechnung von Non-HDL-Cholesterin.\r" +
                "OBR|10||0555890287^IMD Berlin MVZ||HDL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|10|ST|6HDL^HDL-Cholesterin i.S.||!!Storno|mmol/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|11||0555890287^IMD Berlin MVZ||HDL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|11|CE|7HDL^HDL-Cholesterin i.S.       (Photom.)||1.40|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "NTE|7||          \r" +
                "NTE|8||hoher Risikofaktor für eine Herzerkrankung:\r" +
                "NTE|9||< 1.04 mmol/l  (< 40 mg/dl)\r" +
                "OBR|12||0555890287^IMD Berlin MVZ||LDL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|12|ST|6LDL^LDL-Cholesterin i.S.||!!Storno|mmol/l|||||D|||||Schreiber, Jeannette\r" +
                "NTE|10||          \r" +
                "NTE|11||Zielwerte:\r" +
                "NTE|12||< 1.8 mmol/l  bei sehr hohem Risiko\r" +
                "NTE|13||              >= 50% LDL-Reduktion (wenn der Zielwert\r" +
                "NTE|14||              nicht erreicht werden kann)\r" +
                "NTE|15||< 2.6 mmol/l  bei hohem Risiko\r" +
                "NTE|16||< 3.0 mmol/l  bei moderatem/niedrigem Risiko\r" +
                "OBR|13||0555890287^IMD Berlin MVZ||LDL|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|13|CE|7LDL^LDL-Cholesterin i.S.       (Photom.)||3.6|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "NTE|17||          \r" +
                "NTE|18||Zielwerte:\r" +
                "NTE|19||< 1.8 mmol/l  bei sehr hohem Risiko\r" +
                "NTE|20||              >= 50% LDL-Reduktion (wenn der Zielwert\r" +
                "NTE|21||              nicht erreicht werden kann)\r" +
                "NTE|22||< 2.6 mmol/l  bei hohem Risiko\r" +
                "NTE|23||< 3.0 mmol/l  bei moderatem/niedrigem Risiko\r" +
                "OBR|14||0555890287^IMD Berlin MVZ||FE|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|14|ST|6FE^Eisen i.S.||!!Storno|µmol/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|15||0555890287^IMD Berlin MVZ||FE|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|15|CE|7FE^Eisen i.S.                 (Photom.)||21.2|µmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|16||0555890287^IMD Berlin MVZ||GE|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|16|ST|6GE^Gesamteiweiß i.S.||!!Storno|g/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|17||0555890287^IMD Berlin MVZ||GE|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|17|CE|7GE^Gesamteiweiß i.S.          (Photom.)||70.9|g/l|64.0 - 83.0||||P|||||Schreiber, Jeannette\r" +
                "OBR|18||0555890287^IMD Berlin MVZ||GGTN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|18|ST|6GGTN^GGT i.S.||!!Storno|µmol/s*l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|19||0555890287^IMD Berlin MVZ||GGTN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|19|CE|7GGT^GGT i.S.                   (Photom.)||0.55|µmol/s*l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|20||0555890287^IMD Berlin MVZ||ASATN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|20|ST|6ASATN^ASAT (GOT) i.S.||!!Storno|µmol/s*l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|21||0555890287^IMD Berlin MVZ||ASATN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|21|CE|7ASAT^ASAT (GOT) i.S.            (Photom.)||0.35|µmol/s*l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|22||0555890287^IMD Berlin MVZ||ALATN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|22|ST|6ALATN^ALAT (GPT) i.S.||!!Storno|µmol/s*l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|23||0555890287^IMD Berlin MVZ||ALATN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|23|CE|7ALAT^ALAT (GPT) i.S.            (Photom.)||0.40|µmol/s*l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|24||0555890287^IMD Berlin MVZ||HS|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|24|ST|6HS^Harnsäure i.S.||!!Storno|µmol/l|||||D|||||Schreiber, Jeannette\r" +
                "NTE|24||          \r" +
                "NTE|25||Therapieziel bei symptomatischer Gicht <6 mg/dl (<357 umol/l).\r" +
                "OBR|25||0555890287^IMD Berlin MVZ||HS|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|25|CE|7HS^Harnsäure i.S.             (Photom.)||269|µmol/l|||||P|||||Schreiber, Jeannette\r" +
                "NTE|26||          \r" +
                "NTE|27||Therapieziel bei symptomatischer Gicht <357 µmol/l\r" +
                "NTE|28||(<6 mg/dl).\r" +
                "OBR|26||0555890287^IMD Berlin MVZ||HBA1|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|26|CE|5HBA1^Hämoglobin A1c i. EDTA-Blut||4.8|%|< 5.7||||P|||||Schreiber, Jeannette\r" +
                "OBR|27||0555890287^IMD Berlin MVZ||HBA1|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|27|CE|5HBA1N^Hämoglobin A1c i. EDTA-Blut||29|mmol/mol|< 39.0||||P|||||Schreiber, Jeannette\r" +
                "NTE|29||          \r" +
                "NTE|30||Screening auf Diabetes mellitus:\r" +
                "NTE|31||\r" +
                "NTE|32||<5.7% (<39 mmol/mol)       Kein Hinweis auf Diabetes mellitus.\r" +
                "NTE|33|| 5.7-6.4% (39-47 mmol/mol) Grenzbereich, weitere Abklärung\r" +
                "NTE|34||                           über Nüchternglukose und evtl.\r" +
                "NTE|35||                           oGTT angezeigt.\r" +
                "NTE|36||>6.4% (>47 mmol/mol)       Wert spricht für Diabetes mellitus.\r" +
                "NTE|37||\r" +
                "NTE|38||Therapieverlaufskontrolle bei D.m.:\r" +
                "NTE|39||Zielbereich 6.5-7.5% (48-58 mmol/mol)\r" +
                "OBR|28||0555890287^IMD Berlin MVZ||IGA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|28|ST|6IGA^IgA i.S.                     (Turb.)||!!Storno|mg/dl|||||D|||||Schreiber, Jeannette\r" +
                "OBR|29||0555890287^IMD Berlin MVZ||IGA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|29|CE|7IGA^IgA i.S.                     (Turb.)||1.85|g/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|30||0555890287^IMD Berlin MVZ||IGE|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|30|CE|5IGE^IgE i.S. (FEIA) (3rd WHO)||14.3|kU/l|< 85.0||||P|||||Schreiber, Jeannette\r" +
                "OBR|31||0555890287^IMD Berlin MVZ||IGG|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|31|ST|6IGG^IgG i.S.                     (Turb.)||!!Storno|mg/dl|||||D|||||Schreiber, Jeannette\r" +
                "OBR|32||0555890287^IMD Berlin MVZ||IGG|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|32|CE|7IGG^IgG i.S.                     (Turb.)||14.1|g/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|33||0555890287^IMD Berlin MVZ||IGM|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|33|ST|6IGM^IgM i.S.                     (Turb.)||!!Storno|mg/dl|||||D|||||Schreiber, Jeannette\r" +
                "OBR|34||0555890287^IMD Berlin MVZ||IGM|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|34|CE|7IGM^IgM i.S.                     (Turb.)||1.93|g/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|35||0555890287^IMD Berlin MVZ||K|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|35|ST|6K^Kalium i.S. (ISE)||!!Storno|mmol/l|3.3 - 5.1||||D|||||Schreiber, Jeannette\r" +
                "OBR|36||0555890287^IMD Berlin MVZ||K|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|36|CE|7K^Kalium i.S.                    (ISE)||5.0|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|37||0555890287^IMD Berlin MVZ||CREA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|37|ST|6CREA^Kreatinin i.S.||!!Storno|µmol/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|38||0555890287^IMD Berlin MVZ||CREA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|38|CE|7CREA^Kreatinin i.S.                (enz.)||83|µmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|39||0555890287^IMD Berlin MVZ||LDHN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|39|ST|6LDHN^LDH i.S.||!!Storno|µmol/s*l|||||D|||||Schreiber, Jeannette\r" +
                "NTE|40||          \r" +
                "NTE|41||Männer   < 4.22\r" +
                "NTE|42||Frauen   < 4.25\r" +
                "OBR|40||0555890287^IMD Berlin MVZ||LDHN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|40|CE|7LDH^LDH i.S.                   (Photom.)||3.72|µmol/s*l|2.08 - 3.67|++|||P|||||Schreiber, Jeannette\r" +
                "OBR|41||0555890287^IMD Berlin MVZ||LIP|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|41|ST|6LIP^Lipase i.S.||!!Storno|µmol/s*l|< 1.00||||D|||||Schreiber, Jeannette\r" +
                "OBR|42||0555890287^IMD Berlin MVZ||LIP|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|42|CE|7LIP^Lipase i.S.                (Photom.)||0.87|µmol/s*l|< 1.00||||P|||||Schreiber, Jeannette\r" +
                "OBR|43||0555890287^IMD Berlin MVZ||NA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|43|ST|6NA^Natrium i.S. (ISE)||!!Storno|mmol/l|136 - 145||||D|||||Schreiber, Jeannette\r" +
                "OBR|44||0555890287^IMD Berlin MVZ||NA|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|44|CE|7NA^Natrium i.S.                   (ISE)||142|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|45||0555890287^IMD Berlin MVZ||PHOS|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|45|ST|6PHOS^Phosphat i.S.||!!Storno|mmol/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|46||0555890287^IMD Berlin MVZ||PHOS|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|46|CE|7PHOS^Phosphat i.S.              (Photom.)||1.41|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|47||0555890287^IMD Berlin MVZ||TRAN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|47|ST|6TRAN^Transferrin i.S. (Turbi.)||!!Storno|mg/dl|||||D|||||Schreiber, Jeannette\r" +
                "OBR|48||0555890287^IMD Berlin MVZ||TRAN|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|48|CE|7TRAN^Transferrin i.S.            (Turbi.)||2.90|g/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|49||0555890287^IMD Berlin MVZ||TRI|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|49|ST|6TRI^Triglyceride i.S.||!!Storno|mmol/l|< 1.7||||D|||||Schreiber, Jeannette\r" +
                "NTE|43||          \r" +
                "NTE|44||Referenzbereich nach 12stündiger Nahrungskarenz\r" +
                "OBR|50||0555890287^IMD Berlin MVZ||TRI|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|50|CE|7TRI^Triglyceride i.S.          (Photom.)||2.22|mmol/l|< 1.70|+|||P|||||Schreiber, Jeannette\r" +
                "NTE|45||          \r" +
                "NTE|46||nach 12stündiger Nahrungskarenz: <1.70 mmol/l (<150 mg/dl)\r" +
                "NTE|47||\r" +
                "NTE|48||grenzwertig hoch: 1.70 - 2.25 mmol/l  (150 - 199 mg/dl)\r" +
                "NTE|49||hoch            : 2.26 - 5.64 mmol/l  (200 - 499 mg/dl)\r" +
                "NTE|50||sehr hoch       :      > 5.65 mmol/l  (    > 500 mg/dl)\r" +
                "OBR|51||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|51|CE|5LEUKO^Leukozyten||6.8|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|52||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|52|CE|5ERY^Erythrozyten||5.11|Tpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|53||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|53|CE|5HB^Hämoglobin||9.2|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|54||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|54|CE|5HK^Hämatokrit||0.45|l/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|55||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|55|CE|5MCV^MCV (Hk/Ery-Zahl)||88|fl|||||P|||||Schreiber, Jeannette\r" +
                "OBR|56||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|56|CE|5MCH^MCH (Hb/Ery-Zahl)||1.80|fmol|||||P|||||Schreiber, Jeannette\r" +
                "OBR|57||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|57|CE|5MCHC^MCHC (Hb/Hk)||20.5|mmol/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|58||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|58|CE|5THROM^Thrombozyten||291|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|59||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|59|ST|5DIFF^Differentialblutbild (automat.)||.||||||P|||||Schreiber, Jeannette\r" +
                "NTE|51||          \r" +
                "OBR|60||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|60|CE|NEUTRO^ neutrophile Granulozyten||66.8|%|||||P|||||Schreiber, Jeannette\r" +
                "OBR|61||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|61|CE|LYMPH^ Lymphozyten||20.3|%|||||P|||||Schreiber, Jeannette\r" +
                "OBR|62||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|62|CE|MONO^ Monozyten||8.9|%|||||P|||||Schreiber, Jeannette\r" +
                "OBR|63||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|63|CE|EOS^ eosinophile Granulozyten||3.4|%|||||P|||||Schreiber, Jeannette\r" +
                "OBR|64||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|64|CE|BASO^ basophile Granulozyten||0.6|%|||||P|||||Schreiber, Jeannette\r" +
                "OBR|65||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|65|CE|5RDW^RDW-CV:||12.7|%|11.5   - 15.0||||P|||||Schreiber, Jeannette\r" +
                "NTE|52||          \r" +
                "NTE|53||RDW-CV = ERY-Volumenverteilungsbreite\r" +
                "OBR|66||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|66|ST|COM1^Kommentar||!!Storno||||||D|||||Schreiber, Jeannette\r" +
                "OBR|67||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|67|CE|GRANU^ unreife Granulozyten||1.7|%|< 0.6||||P|||||Schreiber, Jeannette\r" +
                "OBR|68||0555890287^IMD Berlin MVZ||BS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|68|ST|NRBC^ Erythroblasten||!!Storno|%|||||D|||||Schreiber, Jeannette\r" +
                "OBR|69||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|69|ST|5DIABS^Differentialblutbild (absolut)||.||||||P|||||Schreiber, Jeannette\r" +
                "NTE|54||          \r" +
                "OBR|70||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|70|ST|5NEUTA^ neutrophile Granulozyten||folgt|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|71||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|71|ST|5LYMA^ Lymphozyten||folgt|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|72||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|72|ST|5MONA^ Monozyten||folgt|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|73||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|73|ST|5EOSA^ eosinophile Granulozyten||folgt|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|74||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|74|ST|5BASA^ basophile Granulozyten||folgt|Gpt/l|||||P|||||Schreiber, Jeannette\r" +
                "OBR|75||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|75|ST|5GRANA^ unreife Granulozyten||folgt|Gpt/l|< 0.06||||P|||||Schreiber, Jeannette\r" +
                "OBR|76||0555890287^IMD Berlin MVZ||DIFABS|||202211230952|||055589028702||LABOR||EBL|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|76|ST|5NRBCA^ Erythroblasten||!!Storno|Gpt/l|||||D|||||Schreiber, Jeannette\r" +
                "OBR|77||0555890287^IMD Berlin MVZ||QUOT1|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|77|ST|6QUOT1^LDL/HDL  Quotient||!!Storno||||||D|||||Schreiber, Jeannette\r" +
                "OBR|78||0555890287^IMD Berlin MVZ||QUOT1|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|78|CE|7QUOT1^LDL/HDL  Quotient||2.57||||||P|||||Schreiber, Jeannette\r" +
                "OBR|79||0555890287^IMD Berlin MVZ||TRANS|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||D||||||||||||||||||||||||0\r" +
                "OBX|79|ST|6TRANS^Transferrin-Sättigung||!!Storno|%|||||D|||||Schreiber, Jeannette\r" +
                "OBR|80||0555890287^IMD Berlin MVZ||TRANS|||202211230952|||055589028701||LABOR||VB|TESTX|||||||||P||||||||||||||||||||||||0\r" +
                "OBX|80|CE|7TRANS^Transferrin-Sättigung||29.1|%|||||P|||||Schreiber, Jeannette\r" +
                "\r"

    fun initData() {
        try {
            val parser = PipeParser()
            val messagen = parser.parse(textform)

            savedStateHandle["patient_Name"] = messagen["PID"][5].value
            savedStateHandle["patient_DateOfBirth"] = formatiereDatum(messagen["PID"][7].value.toString())
            savedStateHandle["patient_Tagebuchnummer"] = messagen["ORC"][3].value.toString()

            savedStateHandle["filledMessage"] = messagen.toString()


            // Find all nested OBX segments
            var obxList = messagen.asIterable().filter { it.name == "OBX" }

            savedStateHandle["obxListfilled"] = obxList
            //filledMessage = messagen.toString()

            // Iteriere durch alle Segmente
            for (segment in obxList) {
                // Überprüfe, ob das aktuelle Segment ein "OBX"-Segment ist
                if (segment.name == "OBX") {
                    //println("Found OBX Segment:")

                    if(segment[7].value != null) {
                        savedStateHandle["befunde"] = befunde.value + 1
                        print("BEFUNDE : ${befunde.value}")
                    }


                    //print(segment[3].value)
                    segment[3][2].value

                    // Zugreifen auf die Felder im Segment

                    println("---------------")
                }
            }


        } catch (e: HL7Exception) {
            e.printStackTrace()
        }
    }


    fun formatiereDatum(datumString: String): String {
        val ursprungsFormat = SimpleDateFormat("yyyyMMdd")
        val zielFormat = SimpleDateFormat("dd.MM.yyyy")

        try {
            val datum = ursprungsFormat.parse(datumString)
            return zielFormat.format(datum).toString()
        } catch (e: Exception) {
            return "Ungültiges Datum"
        }
    }

//    fun getPatientName(): String {
//        return patient_Name
//    }
//
//    fun getPatientBirthOfDate(): String {
//        return patient_DateOfBirth
//    }
//
//    fun getPatientTagebuchnummer(): String {
//        return patient_Tagebuchnummer
//    }
//
//    fun getOBXList(): String {
//        return filledMessage
    }

