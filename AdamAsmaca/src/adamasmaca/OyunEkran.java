/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adamasmaca;

/**
 *
 * @author msı
 */
public class OyunEkran extends javax.swing.JFrame {
    private int yanlisSayisi = 0;
    private String yanlisHarfler = "";
    private boolean ipucuKullanildi = false;
private boolean oyunBitti = false;
private String hedefKelime = "";
private boolean[] tahminEdildi;
private java.util.Set<Character> kullanilanHarfler;
private java.util.List<String> yanlisKelimeler;
private long gecenSaniye = 0;
private javax.swing.Timer sayac;
private javax.swing.JLabel[] harfLabellar;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OyunEkran.class.getName());

    /**
     * Creates new form OyunEkran
     */
   public OyunEkran() {
    initComponents();
    
   
    getContentPane().setBackground(new java.awt.Color(20, 20, 35));
    
  
    OyunOynama.setBackground(new java.awt.Color(20, 20, 35));
    OyunOynama.setForeground(java.awt.Color.WHITE);
    

    pnlOyun.setBackground(new java.awt.Color(20, 20, 35));
    pnlSkorlar.setBackground(new java.awt.Color(20, 20, 35));
    pnlLoglar.setBackground(new java.awt.Color(20, 20, 35));
    pnlHarfler.setBackground(new java.awt.Color(20, 20, 35));
    
  
    lblDurum.setForeground(new java.awt.Color(255, 210, 60));
    lblDurum.setBackground(new java.awt.Color(30, 30, 52));
    lblDurum.setOpaque(true);
    lblYanlis.setForeground(new java.awt.Color(255, 110, 110));
    lblSure.setForeground(new java.awt.Color(90, 210, 90));
    lblResim.setBackground(new java.awt.Color(30, 30, 50));
    lblResim.setOpaque(true);
    lblHarfT.setForeground(java.awt.Color.WHITE);
    lblKelimeT.setForeground(java.awt.Color.WHITE);
    
   
    txtHarf.setBackground(new java.awt.Color(40, 40, 65));
    txtHarf.setForeground(java.awt.Color.WHITE);
    txtHarf.setCaretColor(java.awt.Color.WHITE);
    txtKelime.setBackground(new java.awt.Color(40, 40, 65));
    txtKelime.setForeground(java.awt.Color.WHITE);
    txtKelime.setCaretColor(java.awt.Color.WHITE);
    

    btnHarf.setBackground(new java.awt.Color(28, 130, 28));
    btnHarf.setForeground(java.awt.Color.WHITE);
    btnKelime.setBackground(new java.awt.Color(180, 90, 0));
    btnKelime.setForeground(java.awt.Color.WHITE);
    btnYeniOyun.setBackground(new java.awt.Color(170, 35, 35));
    btnYeniOyun.setForeground(java.awt.Color.WHITE);
    btnTemizleSkor.setBackground(new java.awt.Color(160, 35, 35));
    btnTemizleSkor.setForeground(java.awt.Color.WHITE);
    btnTemizleLog.setBackground(new java.awt.Color(160, 35, 35));
    btnTemizleLog.setForeground(java.awt.Color.WHITE);
    
  
    tblSkorlar.setBackground(new java.awt.Color(28, 28, 50));
    tblSkorlar.setForeground(java.awt.Color.WHITE);
    tblSkorlar.setGridColor(new java.awt.Color(55, 55, 90));
    tblSkorlar.getTableHeader().setBackground(new java.awt.Color(40, 40, 75));
    tblSkorlar.getTableHeader().setForeground(new java.awt.Color(255, 210, 60));
    
    jScrollPane2.getViewport().setBackground(new java.awt.Color(28, 28, 50));
    jScrollPane1.getViewport().setBackground(new java.awt.Color(28, 28, 50));

    tblLoglar.getTableHeader().setBackground(new java.awt.Color(40, 40, 75));
    tblLoglar.getTableHeader().setForeground(new java.awt.Color(255, 210, 60));
    tblLoglar.setBackground(new java.awt.Color(28, 28, 50));
    tblLoglar.setForeground(java.awt.Color.WHITE);
    tblLoglar.setGridColor(new java.awt.Color(55, 55, 90));
    
    pnlSozluk.setBackground(new java.awt.Color(20, 20, 35));

    tblSozluk.setBackground(new java.awt.Color(28, 28, 50));
    tblSozluk.setForeground(java.awt.Color.WHITE);
    tblSozluk.setGridColor(new java.awt.Color(55, 55, 90));
    tblSozluk.getTableHeader().setBackground(new java.awt.Color(40, 40, 75));
    tblSozluk.getTableHeader().setForeground(new java.awt.Color(255, 210, 60));

    jScrollPane3.getViewport().setBackground(new java.awt.Color(28, 28, 50));
    
    this.setTitle("Berkan'ın Adam Asmaca Oyunu");
    this.setSize(900, 650);
    this.setLocationRelativeTo(null);
    OyunOynama.addChangeListener(e -> {
        int idx = OyunOynama.getSelectedIndex();
        if (idx == 1) tabloYenile(tblSkorlar, DosyaYonetici.OYUNLAR_DOSYA, "skor");
        else if (idx == 2) tabloYenile(tblLoglar, DosyaYonetici.LOG_DOSYA, "log");
        else if (idx == 3) sozlukTablosuDoldur(); // Sadece kullanıcı 4. sekmeye tıklarsa dolacak
    });
    yeniOyun();
} 
        private void yeniOyun() {
    if (sayac != null) sayac.stop();
    hedefKelime = DosyaYonetici.rastgeleKelime();
    tahminEdildi = new boolean[hedefKelime.length()];
    kullanilanHarfler = new java.util.LinkedHashSet<>();
    yanlisKelimeler = new java.util.ArrayList<>();
    yanlisSayisi = 0;
    yanlisHarfler = "";
    lblYanlisHarfler.setText("Yanlışlar: ");
    ipucuKullanildi = false;
    btnIpucu.setEnabled(true);
    oyunBitti = false;
    gecenSaniye = 0;
    txtHarf.setEnabled(true);
    txtKelime.setEnabled(true);
    txtHarf.setText("");
    txtKelime.setText("");
    lblDurum.setText("Oyun Başladı!");
    lblYanlis.setText("Yanlış: 0 / 11");
    lblSure.setText("Süre: 0 sn");
    harfPaneliOlustur();
    resimGuncelle();
    sayac = new javax.swing.Timer(1000, e -> {
        gecenSaniye++;
        lblSure.setText("Süre: " + gecenSaniye + " sn");
    });
    sayac.start();
}

private void harfPaneliOlustur() {
    pnlHarfler.removeAll();
    harfLabellar = new javax.swing.JLabel[hedefKelime.length()];
    for (int i = 0; i < hedefKelime.length(); i++) {
        harfLabellar[i] = new javax.swing.JLabel("*");
        harfLabellar[i].setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 22));
        harfLabellar[i].setForeground(new java.awt.Color(30, 100, 200));
        harfLabellar[i].setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(30, 100, 200)));
        harfLabellar[i].setPreferredSize(new java.awt.Dimension(32, 36));
        harfLabellar[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlHarfler.add(harfLabellar[i]);
    }
    pnlHarfler.revalidate();
    pnlHarfler.repaint();
}

private void resimGuncelle() {
    int no = Math.min(yanlisSayisi, 11);
    String yol = DosyaYonetici.RESIMLER_KLASOR + "\\" + no + ".jpg";
    java.io.File f = new java.io.File(yol);
    if (f.exists()) {
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(yol);
        java.awt.Image scaled = icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lblResim.setIcon(new javax.swing.ImageIcon(scaled));
        lblResim.setText("");
    } else {
        lblResim.setIcon(null);
        lblResim.setText("Resim yok: " + no + ".jpg");
    }
}

private void harfTahmin() {
    if (oyunBitti) return;
    String girdi = txtHarf.getText().trim().toUpperCase(new java.util.Locale("tr", "TR"));
    if (girdi.matches(".*[ÇĞİÖŞÜçğışöşü].*")) {
    javax.swing.JOptionPane.showMessageDialog(this, 
        "Lütfen Türkçe karakter kullanmayın!\n(ç→c, ğ→g, ı→i, ö→o, ş→s, ü→u)", 
        "Uyarı", 
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
    txtHarf.setText("");
    if (girdi.isEmpty() || girdi.length() != 1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Tek harf girin!", "Uyarı", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    char harf = girdi.charAt(0);
    if (!Character.isLetter(harf)) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Lütfen sadece harf giriniz! (*, -, 1 vb. semboller kullanılamaz)", 
            "Geçersiz Karakter", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (kullanilanHarfler.contains(harf)) {
        javax.swing.JOptionPane.showMessageDialog(this, "Bu harfi zaten denediniz!", "Uyarı", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    kullanilanHarfler.add(harf);
    boolean bulundu = false;
    for (int i = 0; i < hedefKelime.length(); i++) {
        if (hedefKelime.charAt(i) == harf) {
            tahminEdildi[i] = true;
            harfLabellar[i].setText(String.valueOf(harf));
            harfLabellar[i].setForeground(new java.awt.Color(0, 150, 0));
            bulundu = true;
        }
    }
    if (!bulundu) yanlisSayisi++;
    if (!bulundu) {
    if (!yanlisHarfler.contains(String.valueOf(harf))) {
        yanlisHarfler += harf + " ";
        lblYanlisHarfler.setText("Yanlışlar: " + yanlisHarfler);
    }
}
    resimGuncelle();
    durumGuncelle();
    kazandiMiKontrol();
}

private void ipucuKullan() {
    if (oyunBitti) return;
    
    if (ipucuKullanildi) {
        javax.swing.JOptionPane.showMessageDialog(this, "Bu kelime için 1 adet ipucu hakkınızı zaten kullandınız!", "Uyarı", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    int secim = javax.swing.JOptionPane.showConfirmDialog(this, 
        "Her kelime için sadece 1 tane ipucu hakkınız var.\nKullanmak istediğinize emin misiniz?", 
        "İpucu Onayı", 
        javax.swing.JOptionPane.YES_NO_OPTION, 
        javax.swing.JOptionPane.QUESTION_MESSAGE);

    if (secim != javax.swing.JOptionPane.YES_OPTION) {
        return; 
    }

    java.util.List<Character> bulunmayanlar = new java.util.ArrayList<>();
    for (int i = 0; i < hedefKelime.length(); i++) {
        if (!tahminEdildi[i]) {
            bulunmayanlar.add(hedefKelime.charAt(i));
        }
    }

    if (bulunmayanlar.isEmpty()) return;

    java.util.Random rand = new java.util.Random();
    char ipucuHarf = bulunmayanlar.get(rand.nextInt(bulunmayanlar.size()));

    kullanilanHarfler.add(ipucuHarf);
    for (int i = 0; i < hedefKelime.length(); i++) {
        if (hedefKelime.charAt(i) == ipucuHarf) {
            tahminEdildi[i] = true;
            harfLabellar[i].setText(String.valueOf(ipucuHarf));
            harfLabellar[i].setForeground(new java.awt.Color(0, 150, 0)); 
        }
    }

    ipucuKullanildi = true;
    btnIpucu.setEnabled(false);

    javax.swing.JOptionPane.showMessageDialog(this, "İpucu kullanıldı!\nAçılan Harf: " + ipucuHarf, "İpucu Alındı 💡", javax.swing.JOptionPane.INFORMATION_MESSAGE);

    kazandiMiKontrol();
}

private void kelimeTahmin() {
    if (oyunBitti) return;
    String girdi = txtKelime.getText().trim().toUpperCase(new java.util.Locale("tr", "TR"));
    if (girdi.matches(".*[ÇĞİÖŞÜçğışöşü].*")) {
    javax.swing.JOptionPane.showMessageDialog(this, 
        "Lütfen Türkçe karakter kullanmayın!\n(ç→c, ğ→g, ı→i, ö→o, ş→s, ü→u)", 
        "Uyarı", 
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
    txtKelime.setText("");
    if (girdi.isEmpty()) return;
    if (girdi.length() < 6) {
    javax.swing.JOptionPane.showMessageDialog(this, 
        "Kelimeler en az 6 harfli!", 
        "Uyarı", 
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
    if (girdi.equals(hedefKelime)) {
        for (int i = 0; i < hedefKelime.length(); i++) {
            tahminEdildi[i] = true;
            harfLabellar[i].setText(String.valueOf(hedefKelime.charAt(i)));
            harfLabellar[i].setForeground(new java.awt.Color(0, 150, 0));
        }
        kazandiMiKontrol();
    } else {
        yanlisSayisi++;
        yanlisKelimeler.add(girdi);
        resimGuncelle();
        durumGuncelle();
    }
}

private void durumGuncelle() {
    lblYanlis.setText("Yanlış: " + yanlisSayisi + " / 11");
    if (yanlisSayisi >= 11) oyunBitti();
}

private void kazandiMiKontrol() {
    for (boolean b : tahminEdildi) if (!b) { durumGuncelle(); return; }
    sayac.stop();
    oyunBitti = true;
    txtHarf.setEnabled(false);
    txtKelime.setEnabled(false);
    lblDurum.setText("Tebrikler! " + hedefKelime);
    DosyaYonetici.oyunKaydet("KAZANDI", gecenSaniye);
    DosyaYonetici.gecmisKaydet(hedefKelime, "KAZANDI");
    javax.swing.JOptionPane.showMessageDialog(this, "Tebrikler! Kelime: " + hedefKelime + "\nSüre: " + gecenSaniye + " sn", "Kazandınız! 🎉", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    
}

private void oyunBitti() {
    sayac.stop();
    oyunBitti = true;
    txtHarf.setEnabled(false);
    txtKelime.setEnabled(false);
    for (int i = 0; i < hedefKelime.length(); i++) {
        harfLabellar[i].setText(String.valueOf(hedefKelime.charAt(i)));
        harfLabellar[i].setForeground(new java.awt.Color(200, 0, 0));
    }
    lblDurum.setText("Oyun Bitti! " + hedefKelime);
    DosyaYonetici.gecmisKaydet(hedefKelime, "KAYBETTI");
    DosyaYonetici.oyunKaydet("KAYBETTI", gecenSaniye);
    javax.swing.JOptionPane.showMessageDialog(this, 
    "Kaybettiniz! Kelime: " + hedefKelime + "\nSüre: " + gecenSaniye + " sn\nO senin sorunun! OSS :)", 
    "Oyun Bitti! ", 
    javax.swing.JOptionPane.ERROR_MESSAGE);

}

private void tabloYenile(javax.swing.JTable tablo, String dosyaYolu, String tur) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tablo.getModel();
    model.setRowCount(0);
    java.util.List<String> satirlar = DosyaYonetici.dosyaOku(dosyaYolu);
    int no = 1;
    for (String satir : satirlar) {
        String[] p = satir.split("\\|");
        if (tur.equals("skor") && p.length >= 3) {
            model.addRow(new Object[]{no++, p[0].trim(), p[1].trim(), p[2].trim()});
        } else if (tur.equals("log") && p.length >= 2) {
            model.addRow(new Object[]{no++, p[0].trim(), p[1].trim()});
        }
    }
    }
private void sozlukTablosuDoldur() {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblSozluk.getModel();
    model.setRowCount(0); 

    tblSozluk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    tblSozluk.getColumnModel().getColumn(0).setPreferredWidth(40);  
    tblSozluk.getColumnModel().getColumn(1).setPreferredWidth(150); 
    tblSozluk.getColumnModel().getColumn(2).setPreferredWidth(200); 
    tblSozluk.getColumnModel().getColumn(3).setPreferredWidth(600); 
    

    jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    java.util.List<String> gecmis = DosyaYonetici.dosyaOku(DosyaYonetici.KELIMELER_GECMIS);
    java.util.Set<String> oynananKelimeler = new java.util.HashSet<>();
    for (String satir : gecmis) {
        String[] parcalar = satir.split("\\|");
        if (parcalar.length > 0) {
            oynananKelimeler.add(parcalar[0].trim());
        }
    }
    Object[][] veriler = {
        {"DIRENCLER", "Elektrik Devre Temelleri", "Elektrik akımına karşı zorluk gösteren devre elemanları."},
        {"KONDANSATOR", "Elektrik Devre Temelleri", "Elektrik enerjisini elektrik alanında depolayan devre elemanı."},
        {"TRANSISTOR", "Elektrik Devre Temelleri", "Sinyali yükselten yarı iletken devre elemanı."},
        {"GERILIM", "Elektrik Devre Temelleri", "İki nokta arasındaki potansiyel enerji farkı (Volt)."},
        {"KAPASITANS", "Elektrik Devre Temelleri", "Kondansatörün elektrik yükü depolama kapasitesi."},
        {"OSILOSKOP", "Elektrik Devre Temelleri", "Elektriksel sinyallerin değişimini görsel olarak gösteren cihaz."},
        
        {"MANTIKKAPISI", "Lojik Devreler 2", "Dijital devrelerin temel mantıksal işlemlerini yapan yapı taşları."},
        {"NANDKAPISI", "Lojik Devreler 2", "VE Değil (Not-AND) mantıksal işlemini gerçekleştiren kapı."},
        {"NORKAPISI", "Lojik Devreler 2", "VEYA Değil (Not-OR) mantıksal işlemini gerçekleştiren kapı."},
        {"XORKAPISI", "Lojik Devreler 2", "Özel VEYA (Exclusive-OR) mantıksal işlemini gerçekleştiren kapı."},
        {"KARNAUGH", "Lojik Devreler 2", "Mantıksal (Boole) ifadeleri en yalın haline getirmek için kullanılan harita."},
        {"KODLAYICI", "Lojik Devreler 2", "Aktif olan girişi ikilik koda dönüştüren devre elemanı (Encoder)."},
        
        {"PROGRAMLAMA", "Programlama Dilleri 1", "Bilgisayara belirli işleri yaptırmak için komutlar yazma süreci."},
        {"KAPSULLEME", "Programlama Dilleri 1", "Nesne yönelimli programlamada veri ve metotları sınıf içinde gizleme."},
        {"KALITIM", "Programlama Dilleri 1", "Bir sınıfın üst sınıftaki özellikleri ve metotları miras alması."},
        {"ARAYUZLER", "Programlama Dilleri 1", "Sınıfların hangi metotları uygulayacağını belirten şablon yapılar."},
        {"SINIFLAR", "Programlama Dilleri 1", "Nesnelerin özelliklerini ve davranışlarını tanımlayan ana şablonlar."},
        {"NESNELER", "Programlama Dilleri 1", "Bellekte hayat bowling, sınıflardan üretilmiş somut örnekler."},
        
        {"VERITABANI", "Veri Tabanı", "Düzenli ve yapılandırılmış verilerin bilgisayarda depolandığı sistem."},
        {"NORMALFORM", "Veri Tabanı", "Veritabanında veri tekrarını ve tutarsızlığı önleme kuralları."},
        {"TABLOSEMA", "Veri Tabanı", "Bir veritabanı tablosunun yapısını ve veri tiplerini tanımlayan şema."},
        {"BIRINCILANAHTAR", "Veri Tabanı", "Tablodaki her satırı benzersiz bir şekilde tanımlayan sütun (Primary Key)."},
        {"YABANCILANAHTAR", "Veri Tabanı", "İki tabloyu birbirine bağlayan referans anahtarı (Foreign Key)."},
        {"INDEKSLEME", "Veri Tabanı", "Veritabanındaki verilere erişim hızını artırmak için kullanılan mekanizma."},
        
        {"DETERMINISTIK", "Biçimsel Diller ve Otomatlar", "Sonraki durumu tamamen belirli ve tek olan otomat türü (DFA)."},
        {"OTOMAT", "Biçimsel Diller ve Otomatlar", "Dilleri tanımak için kullanılan soyut matematiksel makineler."},
        {"ALFABELER", "Biçimsel Diller ve Otomatlar", "Bir dilin oluşturulmasında kullanılan sonlu semboller kümesi."},
        {"GRAMERLER", "Biçimsel Diller ve Otomatlar", "Dilde kurallara uygun cümleler üretilmesini sağlayan kurallar kümesi."},
        {"SONDURUM", "Biçimsel Diller ve Otomatlar", "Girdinin otomat tarafından kabul edildiğini gösteren bitiş durumu."},
        {"BASLANGIC", "Biçimsel Diller ve Otomatlar", "Otomatın hesaplama sürecine başladığı ilk durum (Start State)."}
    };

    int siraNo = 1;
    for (int i = 0; i < veriler.length; i++) {
        String tabloKelimesi = veriler[i][0].toString();
        if (oynananKelimeler.contains(tabloKelimesi)) {
            model.addRow(new Object[]{ siraNo++, veriler[i][0], veriler[i][1], veriler[i][2] });
        }
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OyunOynama = new javax.swing.JTabbedPane();
        pnlOyun = new javax.swing.JPanel();
        lblResim = new javax.swing.JLabel();
        lblYanlis = new javax.swing.JLabel();
        lblSure = new javax.swing.JLabel();
        lblDurum = new javax.swing.JLabel();
        pnlHarfler = new javax.swing.JPanel();
        lblHarfT = new javax.swing.JLabel();
        lblKelimeT = new javax.swing.JLabel();
        txtHarf = new javax.swing.JTextField();
        btnHarf = new javax.swing.JButton();
        txtKelime = new javax.swing.JTextField();
        btnKelime = new javax.swing.JButton();
        btnYeniOyun = new javax.swing.JButton();
        btnIpucu = new javax.swing.JButton();
        lblYanlisHarfler = new javax.swing.JLabel();
        pnlSkorlar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSkorlar = new javax.swing.JTable();
        btnTemizleSkor = new javax.swing.JButton();
        pnlLoglar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoglar = new javax.swing.JTable();
        btnTemizleLog = new javax.swing.JButton();
        pnlSozluk = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSozluk = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniBasla = new javax.swing.JMenuItem();
        mniYeniden = new javax.swing.JMenuItem();
        mniCikis = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniHakkında = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblYanlis.setText("Yanlış : 0/11");

        lblSure.setText("Süre : 0 sn");

        lblDurum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDurum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDurum.setText("Oyun Başladı!");
        lblDurum.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblHarfT.setText("Harf Tahmini : ");

        lblKelimeT.setText("Kelime Tahmini : ");

        txtHarf.addActionListener(this::txtHarfActionPerformed);

        btnHarf.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHarf.setText("Harf Dene ");
        btnHarf.addActionListener(this::btnHarfActionPerformed);

        txtKelime.addActionListener(this::txtKelimeActionPerformed);

        btnKelime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKelime.setText("Kelime Dene");
        btnKelime.addActionListener(this::btnKelimeActionPerformed);

        btnYeniOyun.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnYeniOyun.setText("Yeni Oyun ");
        btnYeniOyun.addActionListener(this::btnYeniOyunActionPerformed);

        btnIpucu.setBackground(new java.awt.Color(255, 255, 51));
        btnIpucu.setText("İpucu Al ");
        btnIpucu.addActionListener(this::btnIpucuActionPerformed);

        lblYanlisHarfler.setBackground(new java.awt.Color(200, 0, 0));
        lblYanlisHarfler.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblYanlisHarfler.setText("Yanlışlar :");

        javax.swing.GroupLayout pnlOyunLayout = new javax.swing.GroupLayout(pnlOyun);
        pnlOyun.setLayout(pnlOyunLayout);
        pnlOyunLayout.setHorizontalGroup(
            pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOyunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblResim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYanlis, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(lblSure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIpucu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYanlisHarfler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnYeniOyun, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(lblDurum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHarfler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOyunLayout.createSequentialGroup()
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHarfT, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnHarf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addComponent(txtHarf, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblKelimeT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKelime)
                            .addComponent(btnKelime, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        pnlOyunLayout.setVerticalGroup(
            pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOyunLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addComponent(lblDurum, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlHarfler, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblResim, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHarfT, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(lblKelimeT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHarf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKelime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKelime, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnYeniOyun, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblYanlisHarfler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblYanlis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSure, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIpucu)
                        .addGap(19, 19, 19))))
        );

        OyunOynama.addTab("Oyun Oynama ", pnlOyun);

        tblSkorlar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblSkorlar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Tarih & Saat", "Sonuç", "Süre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSkorlar);

        btnTemizleSkor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTemizleSkor.setText("Temizle");
        btnTemizleSkor.addActionListener(this::btnTemizleSkorActionPerformed);

        javax.swing.GroupLayout pnlSkorlarLayout = new javax.swing.GroupLayout(pnlSkorlar);
        pnlSkorlar.setLayout(pnlSkorlarLayout);
        pnlSkorlarLayout.setHorizontalGroup(
            pnlSkorlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkorlarLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(pnlSkorlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(btnTemizleSkor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        pnlSkorlarLayout.setVerticalGroup(
            pnlSkorlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkorlarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTemizleSkor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        OyunOynama.addTab("Eski Skorlar", pnlSkorlar);

        tblLoglar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblLoglar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "#", "Etiket", "Tarih & Zaman "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblLoglar);

        btnTemizleLog.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTemizleLog.setText("Temizle");
        btnTemizleLog.addActionListener(this::btnTemizleLogActionPerformed);

        javax.swing.GroupLayout pnlLoglarLayout = new javax.swing.GroupLayout(pnlLoglar);
        pnlLoglar.setLayout(pnlLoglarLayout);
        pnlLoglarLayout.setHorizontalGroup(
            pnlLoglarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoglarLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(pnlLoglarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(btnTemizleLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        pnlLoglarLayout.setVerticalGroup(
            pnlLoglarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoglarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnTemizleLog, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OyunOynama.addTab("Giriş Logları ", pnlLoglar);

        pnlSozluk.setBackground(new java.awt.Color(20, 20, 35));

        tblSozluk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Kelime", "Ders", "Anlam"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSozluk);

        javax.swing.GroupLayout pnlSozlukLayout = new javax.swing.GroupLayout(pnlSozluk);
        pnlSozluk.setLayout(pnlSozlukLayout);
        pnlSozlukLayout.setHorizontalGroup(
            pnlSozlukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSozlukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSozlukLayout.setVerticalGroup(
            pnlSozlukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSozlukLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );

        OyunOynama.addTab("Kelime Sözlüğü", pnlSozluk);

        jMenu1.setText("Oyun");

        mniBasla.setText("Oyuna Başla ");
        mniBasla.addActionListener(this::mniBaslaActionPerformed);
        jMenu1.add(mniBasla);

        mniYeniden.setText("Yeniden Başla");
        mniYeniden.addActionListener(this::mniYenidenActionPerformed);
        jMenu1.add(mniYeniden);

        mniCikis.setText("Çıkış");
        mniCikis.addActionListener(this::mniCikisActionPerformed);
        jMenu1.add(mniCikis);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Hakkında");

        mniHakkında.setText("Hakkında");
        mniHakkında.addActionListener(this::mniHakkındaActionPerformed);
        jMenu2.add(mniHakkında);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OyunOynama)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(OyunOynama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarfActionPerformed
        // TODO add your handling code here:
        harfTahmin();
    }//GEN-LAST:event_btnHarfActionPerformed

    private void mniBaslaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBaslaActionPerformed
        // TODO add your handling code here:
        yeniOyun();
OyunOynama.setSelectedIndex(0);
        
    }//GEN-LAST:event_mniBaslaActionPerformed

    private void btnKelimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelimeActionPerformed
        // TODO add your handling code here:
        kelimeTahmin();
    }//GEN-LAST:event_btnKelimeActionPerformed

    private void btnYeniOyunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYeniOyunActionPerformed
        // TODO add your handling code here:
        yeniOyun();
    }//GEN-LAST:event_btnYeniOyunActionPerformed

    private void mniYenidenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniYenidenActionPerformed
        // TODO add your handling code here:
        yeniOyun();
OyunOynama.setSelectedIndex(0);
    }//GEN-LAST:event_mniYenidenActionPerformed

    private void mniCikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mniCikisActionPerformed

    private void mniHakkındaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHakkındaActionPerformed
        // TODO add your handling code here:
        javax.swing.JOptionPane.showMessageDialog(this, 
    "Adam Asmaca Oyunu\n\n" +
    "Programlama 2 Ödevi\n" +
    "2025-2026 Bahar Dönemi\n\n" +
    "Kelime Konusu: 2. Sınıf 2. Dönem Dersleri\n" +
    "(Otomat Teorisi, Java, Veritabanı...)\n\n" +
    "Geliştirici: Berkan Kublay\n" +
    "Öğrenci No: 2321032015\n\n" +
    "İyi Oyunlar!", 
    "Hakkında", 
    javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_mniHakkındaActionPerformed

    private void txtHarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHarfActionPerformed
        // TODO add your handling code here:
        harfTahmin();
    }//GEN-LAST:event_txtHarfActionPerformed

    private void txtKelimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKelimeActionPerformed
        // TODO add your handling code here:
        kelimeTahmin();
    }//GEN-LAST:event_txtKelimeActionPerformed

    private void btnTemizleSkorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemizleSkorActionPerformed
        // TODO add your handling code here:
        String girilen = javax.swing.JOptionPane.showInputDialog(this, "Şifre:");
if (girilen != null && girilen.equals(DosyaYonetici.sifreOku())) {
    DosyaYonetici.dosyayiTemizle(DosyaYonetici.OYUNLAR_DOSYA);
    tabloYenile(tblSkorlar, DosyaYonetici.OYUNLAR_DOSYA, "skor");
} else if (girilen != null) {
    javax.swing.JOptionPane.showMessageDialog(this, "Hatalı şifre!");
}
    }//GEN-LAST:event_btnTemizleSkorActionPerformed

    private void btnTemizleLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemizleLogActionPerformed
        // TODO add your handling code here:
        String girilen = javax.swing.JOptionPane.showInputDialog(this, "Şifre:");
if (girilen != null && girilen.equals(DosyaYonetici.sifreOku())) {
    DosyaYonetici.dosyayiTemizle(DosyaYonetici.LOG_DOSYA);
    tabloYenile(tblLoglar, DosyaYonetici.LOG_DOSYA, "log");
} else if (girilen != null) {
    javax.swing.JOptionPane.showMessageDialog(this, "Hatalı şifre!");
}
    }//GEN-LAST:event_btnTemizleLogActionPerformed

    private void btnIpucuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIpucuActionPerformed
        // TODO add your handling code here:
        ipucuKullan();
    }//GEN-LAST:event_btnIpucuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new OyunEkran().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane OyunOynama;
    private javax.swing.JButton btnHarf;
    private javax.swing.JButton btnIpucu;
    private javax.swing.JButton btnKelime;
    private javax.swing.JButton btnTemizleLog;
    private javax.swing.JButton btnTemizleSkor;
    private javax.swing.JButton btnYeniOyun;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDurum;
    private javax.swing.JLabel lblHarfT;
    private javax.swing.JLabel lblKelimeT;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblSure;
    private javax.swing.JLabel lblYanlis;
    private javax.swing.JLabel lblYanlisHarfler;
    private javax.swing.JMenuItem mniBasla;
    private javax.swing.JMenuItem mniCikis;
    private javax.swing.JMenuItem mniHakkında;
    private javax.swing.JMenuItem mniYeniden;
    private javax.swing.JPanel pnlHarfler;
    private javax.swing.JPanel pnlLoglar;
    private javax.swing.JPanel pnlOyun;
    private javax.swing.JPanel pnlSkorlar;
    private javax.swing.JPanel pnlSozluk;
    private javax.swing.JTable tblLoglar;
    private javax.swing.JTable tblSkorlar;
    private javax.swing.JTable tblSozluk;
    private javax.swing.JTextField txtHarf;
    private javax.swing.JTextField txtKelime;
    // End of variables declaration//GEN-END:variables
}

