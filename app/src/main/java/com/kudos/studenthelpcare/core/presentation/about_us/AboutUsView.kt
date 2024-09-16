
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
fun AboutUsView() {
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
         # Tentang StudentHelpCare
         ![banner](https://firebasestorage.googleapis.com/v0/b/student-helpcare.appspot.com/o/images%2FGroup%204024.png?alt=media&token=68a16d3e-2593-4425-b930-86c8e2029552)
        

        Selamat datang di StudentHelpCare, platform terpercaya untuk menyuarakan keprihatinan dan mendorong perubahan positif dalam lingkungan pendidikan Anda.
        
        ## Misi Kami
        
        Di StudentHelpCare, kami percaya bahwa setiap siswa berhak untuk didengar. Misi kami adalah menciptakan saluran yang aman, transparan, dan efisien bagi siswa untuk mengkomunikasikan keprihatinan, saran, dan pengalaman mereka kepada administrator sekolah dan guru.
        
        ## Apa yang Kami Tawarkan
        
        ### 1. Pengajuan Pengaduan yang Mudah
        Dengan antarmuka yang ramah pengguna, Anda dapat dengan mudah memposting keprihatinan atau keluhan Anda. Baik itu tentang masalah akademik, fasilitas, atau aspek lain dari kehidupan sekolah Anda, kami siap mendengarkan.
        
        ### 2. Proses Penanganan yang Transparan
        Kami memahami pentingnya mengetahui perkembangan pengaduan Anda. Itulah sebabnya kami memberikan pembaruan real-time tentang status pengajuan Anda, memastikan Anda selalu mendapat informasi terkini.
        
        ### 3. Komunikasi Langsung dengan Staf
        Platform kami memungkinkan komunikasi langsung antara siswa dan staf sekolah. Administrator dan guru dapat memberikan komentar pada pengajuan Anda, meminta klarifikasi, atau memberikan pembaruan, menciptakan dialog untuk pemecahan masalah yang efektif.
        
        ## Mengapa Memilih StudentHelpCare?
        
        - **Pemberdayaan**: Kami memberi Anda suara untuk membentuk pengalaman pendidikan Anda.
        - **Transparansi**: Pantau kemajuan pengaduan Anda dari pengajuan hingga penyelesaian.
        - **Efisiensi**: Proses kami yang efisien memastikan waktu respons lebih cepat dan penyelesaian masalah.
        - **Kerahasiaan**: Privasi Anda adalah prioritas kami. Bagikan keprihatinan Anda tanpa rasa takut.
        
        ## Tim peneliti
        - Ahmad Nurrayhanwatsiq Ardiansyah [whatsapp](https://wa.me/6281559868586)
        - Etyka Rina Mahanani
        
        ## Tim pengembang
        - Septa Alfauzan [email](mailto:alfauzansepta@gmail.com)
        - Roni Ragil Iman [email](mailto:roniragil11@gmail.com)
        
        
        ## Bergabunglah dengan Kami dalam Menciptakan Perubahan
        
        StudentHelpCare lebih dari sekadar aplikasi – ini adalah gerakan menuju institusi pendidikan yang lebih baik dan lebih responsif. Dengan menggunakan platform kami, Anda tidak hanya menyelesaikan masalah Anda sendiri; Anda berkontribusi pada peningkatan sekolah Anda untuk semua orang.
        
        Unduh StudentHelpCare hari ini dan jadilah bagian dari perubahan yang ingin Anda lihat di sekolah Anda!
        """.trimIndent()
            )
        }
        BasicMarkdown(astNode)
    }
}