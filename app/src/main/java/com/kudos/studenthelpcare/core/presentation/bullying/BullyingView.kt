package com.kudos.studenthelpcare.core.presentation.bullying

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.halilibo.richtext.commonmark.CommonmarkAstNodeParser
import com.halilibo.richtext.markdown.BasicMarkdown
import com.halilibo.richtext.ui.material3.RichText

@Composable
fun BullyingView() {
    RichText(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val parser = remember { CommonmarkAstNodeParser() }
        val astNode = remember(parser) {
            parser.parse(
                """
        # Kenali Bullying
        
        ![banner](https://firebasestorage.googleapis.com/v0/b/student-helpcare.appspot.com/o/images%2Fimage-removebg-preview%20(3).png?alt=media&token=576d95a4-b2ae-4e60-8107-243cabd1f9b9)
        
        &nbsp;Pengertian pendidikan menurut Undang-undang Sistem Pendidikan Nasional No. 20 tahun 2003 yang menyatakan bahwa: Pendidikan adalah suatu usaha sadar dan terencana untuk mewujudkan suasana belajar dan proses pembelajaran agar peserta didik secara aktif mengembangkan potensi dirinya untuk memiliki kekuatan spiritual keagamaan, pengendalian diri, kepribadian, kecerdasan, akhlak mulia, serta keterampilan yang diperlukan dirinya, masyarakat, bangsa dan negara.
        
        Kasus bullying di sekolah dasar kali ini menjadi isu yang menarik perhatian semua orang mengenai dunia pendidikan di sekolah dasar. Kasus bullying ini mencuat dengan beredarnya video rekaman bullying yang di unggah di situs youtube. Sehingga semua orang dapat melihat secara kronologis proses bullying di sekolah dasar tersebut. Psikolog Anak Seto Mulyadi atau akrab disapa Kak Seto mengatakan, salah satu penyebab bullying yang dilakukan oleh anak yaitu dikarenakan institusi pendidikan tidak mengerti suara anak.
        
       &nbsp; Isu bullying di kalangan siswa sekolah dasar ini sangatlah menjadi perhatian dunia pendidikan, dimana pendidikan di sekolah dasar merupakan pendidikan yang dijadikan pondasi untuk membentuk karakter kepribadian anak. Akan tetapi, pada kenyataannya kini telah mencuat isu negatif mengenai kepribadian siswa/siswi tersebut. Masalah bullying di lingkungan sekolah dasar ini harus segera diatasi supaya esensi tujuan pendidikan dasar dapat tercapai sebagaimana mestinya.
        
       &nbsp; Dalam mengatasi permasalahan bullying di lingkungan sekolah dasar, perlu adanya kerjasama antara para praktisi pendidikan, orang tua dan masyarakat sekitar, dimana lingkungan merupakan faktor yang paling utama dalam membentuk kepribadian siswa. Oleh karena itu, siswa sangat memerlukan lingkungan yang kondusif untuk mengembangkan karakteristik dan potensipotensi yang sesuai dengan tingkat perkembangannya. Kesadaran adalah langkah besar dalam membantu untuk mencegah dan mengurangi bullying dan krisis skenario. Pendidik dibebankan dengan membuat sekolah lebih aman, dan melanjutkan kegiatan antikekerasan mereka untuk membantu memastikan bahwa situasi kekerasan dicegah, intimidasi terhalang, dan banyak tragedi mungkin dicegah.
        
        Program-program dalam mengatasi bullying di sekolah dengan menggunakan whole-school approach tersebut harus dirancang secara kolaboratif dari berbagai aspek yang terkait tersebut. Adapun programprogram tersebut dapat dipaparkan sebagai berikut: 
        
        1. Bullying fisik ini merupakan jenis bullying yang kasat mata. Siapa saja bisa melihat tindakan merugikan ini karena terjadi sentuhan fisik antara pelaku dengan korban bullying. Contoh bullying fisik yaitu menampar, menimpuk, menginjak kaki, menjegal, meludahi, memalak, melempar dengan barang.
        2. Program guru Program guru dalam mengatasi dan mencegah bullying di sekolah dasar yaitu dengan cara menciptakan hubungan baik dengan siswa dan melakukan bimbingan yang intensif kepada siswa.
        3. Program orang tua (parenting program) Orang tua harus ikut membantu mencegah dan mengatasi perilaku bullying di sekolah, karna pendidikan yang paling pertama dan utama adalah pendidikan di keluarga. Sehingga alangkah baiknya manakala tgerdapat kerjasama antara pendidikan keluarga yang dilakukan guru dengan pendidikan formal di sekolah dasar yang dilakukan guru-guru dan para praktisi pendidikan lainnya.
        
       &nbsp; Dalam rangka mencegah dan mengatasi bullying di sekolah dasar, maka perlu adanya kerjasama dan hubungan yang baik antara guru, orang tua dan staf-staf sekolah lainnya. Sekolah sebaiknya membuat programprogram yang mengusung sekolah antibullying, dimana prrogram tersebut dapat disosialisakan kepada siswa mengenai masalah perilaku bullying tersebut, mengadapat pertemuan rutin dengan orang tua dan komite sekolah (Firdaus, 2019)
        
        &nbsp;Dikutip halaman kompas Menteri Pendidikan, Kebudayaan, Riset, dan Teknologi (Mendikbud Ristek) Nadiem Makarim mengemukakan, berdasarkan hasil Asesmen Nasional (AN) tahun 2021 dan 2022 atau Rapor Pendidikan 2022 dan 2023, sebanyak 24,4 persen peserta didik mengalami berbagai jenis perundungan (bullying). Korban bullying juga mengalami kekerasan fisik, untuk bullying yang bersifat kekerasan secara fisik. Tindakan kekerasan secara fisik dan verbal yang mereka terima sering menjadi faktor trauma untuk jangka pendek dan jangka panjang.
        
         ![img-2](https://firebasestorage.googleapis.com/v0/b/student-helpcare.appspot.com/o/images%2Fimage-removebg-preview%20(4).png?alt=media&token=04da11c6-4188-4a24-9678-48157d1b0549)
        
       &nbsp; Bullying pada tiga poin, yakni: fisik, verbal, dan hubungan. Bullying fisik berupa tindakan agresi individu terhadap individu lain yang melibatkan anggota fisik, seperti: memukul dan menendang korban, sementara bullying verbal adalah kekerasan yang dilakukan secara verbal (baik melalui lisan maupun tulisan), seperti: menggoda, memanggil dengan sebutan yang menyakitkan, dan mengancam. bullying hubungan ialah di mana pelaku tidak secara langsung menghadapi korban dengan mencoba untuk mengisolasi korban secara sosial dan memisahkan korban dari kelompok sosial.
        
       &nbsp; Selanjutnya, Dupper (2013) menambahkan dua tipe bullying yang termasuk dalam traditional bullying, yakni: bullying seksual dan bullying bias. Bullying seksual merupakan perilaku bullying secara fisik atau verbal yang merujuk pada seksualitas atau identitas gender seseorang (Dupper, 2013). Bullying seksual sebagaimana mengolok-olok seseorang untuk homoseksual, mengolok-olok organ sensitif perempuan, menggunakan istilah seksual (misalnya, perempuan malam) untuk menjatuhkan reputasi orang, menyebarkan rumor tentang kehidupan seks korban, dan menekan seseorang untuk bertindak dengan cara seksual (misalnya, proposisi seksual) (Dupper, 2013; Duncan, 2012). Bullying seksual tidak hanya terjadi pada perempuan saja, namun juga terjadi pada laki-laki.
        
       &nbsp; Bullying tipe yang terakhir, apabila merujuk pada Dupper (2013) adalah bullying bias, dimana korban diserang karena menjadi bagian atau anggota suatu kelompok yang termarginalkan, bukan dikarenakan karakter yang terdapat pada korban. Dalam hal ini, kasus yang sering terjadi adalah ketika individu menjalin pertemanan dengan korban yang sering menerima perlakuan bullying. Individu tersebut dipukul atau dihina karena dia berteman dengan individu yang selalu mendapatkan perlakuan bullying. Cyberbullying merupakan sebuah perilaku bullying yang terjadi di dalam berbagai media teknologi. cyberbullying sebagai "bullying yang dilakukan kepada orang lain melalui email, sms, di chat room, di situs web, atau melalui pesan digital atau gambar yang dikirimkan ke telepon seluler".Salah satu alasan mengapa cyberbullying terbukti sulit untuk didefinisikan adalah bahwa cyberbullying terjadi dalam bentuk yang beragam dan di dalam media yang berbeda-beda (misalnya, game online, situs jejaring sosial, sms, situs web). 
        
       &nbsp; Secara umum praktik bullying dapat dikelompokkan menjadi tiga kategori, yakni bullying fisik, bullying non-fisik, dan bullying mental atau psikologis. 
        
        1.	Bullying fisik ini merupakan jenis bullying yang kasat mata. Siapa saja bisa melihat tindakan merugikan ini karena terjadi sentuhan fisik antara pelaku dengan korban bullying. Contoh bullying fisik yaitu menampar, menimpuk, menginjak kaki, menjegal, meludahi, memalak, melempar dengan barang.
        2.	Bullying verbal ini jenis bullying yang juga bisa terdeteksi karena bisa tertangkap indra pendengaran. Contoh bullying verbal yang kerap tak disadari, di antaranya memaki, menghina menjuluki, meneriaki, mempermalukan di depan umum, menuduh, menyoraki, menebar gossip, dan memfitnah.
        3.	Bullying mental ini jenis bullying yang paling berbahaya karena tidak tertangkap mata atau telinga kita jika kita tidak cukup awas mendeteksinya. Contoh bullying mental yaitu memandang sinis, memandang penuh ancaman, mempermalukan di depan umum, mendiamkan, mengucilkan, mempermalukan, memandang yang merendahkan, memelototi dan mencibir.


       &nbsp; Sementara itu, Kementerian Pemberdayaan Perempuan dan Perlindungan Anak (KemenPPPA), mengelompokan tindakan bullying menjadi enam kategori, yakni: 
        
        1.	Kontak fisik langsung 
        Contoh tindakanya antara lain memukul, mendorong, menggigit, menjambak, menendang, mengunci seseorang dalam ruangan, mencubit, mencakar, memeras, dan merusak barang yang dimiliki orang lain 
        2.	Kontak verbal langsung 
        Contoh tindakanya anatara lain mengancam, mempermalukan, merendahkan, mengganggu, memberi panggilan nama (name-calling), sarkasme, merendahkan (put- downs), mencela atau mengejek, mengintimidasi, memaki, dan menyebarkan gosip 
        3.	Perilaku non-verbal langsung 
        Contoh tindakanya antara lain melihat dengan sinis, menjulurkan lidah, menampilkan ekspresi muka yang merendahkan, mengejek atau mengancam yang biasanya disertai oleh bullying fisik atau verbal 
        4.	Perilaku non-verbal tidak langsung 
        Contoh tindakanya anaara lain mendiamkan seseorang, memanipulasi persahabatan sehingga menjadi retak, dan sengaja mengucilkan atau mengabaikan.
        5.	Cyber bullying Tindakan menyakiti orang lain dengan sarana media elektronik. 
        Contoh tindakanya antara lain rekaman video intimidasi, dan pencemaran nama baik lewat media sosial.
        6.	Pelecehan seksual 
        Kadang tindakan pelecehan dikategorikan perilaku agresi fisik atau verbal.

        Sumber:
        [https://www.kompas.com/edu/read/2023/07/20/182016471/rapor-pendidikan-2022-2023-nadiem-244-persen-siswa-alami-bullying](https://www.kompas.com/edu/read/2023/07/20/182016471/rapor-pendidikan-2022-2023-nadiem-244-persen-siswa-alami-bullying).
        [https://health.kompas.com/read/2020/02/03/102900568/mengenal-jenis-jenis-dan-contoh-perilaku-bullying-yang-kerap-tak-disadari](https://health.kompas.com/read/2020/02/03/102900568/mengenal-jenis-jenis-dan-contoh-perilaku-bullying-yang-kerap-tak-disadari)
        Firdaus, F. M. (2019). Upaya Mengatasi Bullying di Sekolah Dasar dengan Mensinergikan Program Sekolah dan Parenting Program melalui Whole-School Approach. DIDAKTIKA: Jurnal Pendidikan Sekolah Dasar, 2(2), 49–60. [https://doi.org/10.21831/didaktika.v2i2.28098](https://doi.org/10.21831/didaktika.v2i2.28098)


        """.trimIndent()
            )
        }
        BasicMarkdown(astNode)
    }
}