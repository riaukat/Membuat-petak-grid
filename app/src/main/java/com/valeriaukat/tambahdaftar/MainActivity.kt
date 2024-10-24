package com.valeriaukat.tambahdaftar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valeriaukat.tambahdaftar.data.DataSource
import com.valeriaukat.tambahdaftar.model.Topic
import com.valeriaukat.tambahdaftar.ui.theme.TambahDaftarTheme

// Kelas MainActivity merupakan titik masuk utama aplikasi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Mengaktifkan tampilan edge-to-edge untuk aktivitas
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Mengatur tampilan konten aktivitas menggunakan Jetpack Compose
        setContent {
            TambahDaftarTheme {
                // Membuat wadah permukaan dengan warna latar belakang dari tema
                Surface(
                    modifier = Modifier
                        .fillMaxSize() // Mengisi ukuran maksimum yang tersedia
                        .statusBarsPadding(), / Menambahkan padding untuk status bar
                    color = MaterialTheme.colorScheme.background // Mengatur warna latar belakang
                ) {
                    // Menampilkan grid topik dengan padding yang ditentukan
                    TopicGrid(
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small), // Padding di sebelah kiri
                            top = dimensionResource(R.dimen.padding_small), // Padding di bagian atas
                            end = dimensionResource(R.dimen.padding_small), // Padding di sebelah kanan
                        )
                    )
                }
            }
        }
    }
}

// Fungsi komposabel untuk menampilkan grid topik
@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    // Membuat grid vertikal malas dengan kolom tetap
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Mengatur grid memiliki 2 kolom
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak antara item secara vertikal
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak antara item secara horizontal
        modifier = modifier // Menggunakan modifier yang diberikan
    ) {
        // Menghasilkan TopicCard untuk setiap topik dalam DataSource
        items(DataSource.topics) { topic ->
            TopicCard(topic) // Menampilkan kartu topik
        }
    }
}

// Fungsi komposabel untuk menampilkan satu kartu topik
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card { // Membuat kartu untuk mengenkapsulasi konten
        Row { // Mengatur gambar dan teks dalam tata letak horizontal
            Box { // Box untuk menampung gambar
                Image(
                    painter = painterResource(id = topic.imageRes), // Memuat sumber daya gambar
                    contentDescription = null, // Tidak ada deskripsi konten
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp) // Mengatur ukuran gambar
                        .aspectRatio(1f), // Mempertahankan rasio aspek
                    contentScale = ContentScale.Crop // Memotong gambar agar sesuai dengan ukuran
                )
            }

            // Kolom untuk menampilkan informasi teks tentang topik
            Column {
                Text(
                    text = stringResource(id = topic.name), // Mengambil nama topik dari sumber daya
                    style = MaterialTheme.typography.bodyMedium, // Mengatur gaya tipografi
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium), // Padding di sebelah kiri
                        top = dimensionResource(R.dimen.padding_medium), // Padding di bagian atas
                        end = dimensionResource(R.dimen.padding_medium), // Padding di sebelah kanan
                        bottom = dimensionResource(R.dimen.padding_small) // Padding di bagian bawah
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) { // Baris untuk ikon dan jumlah kursus yang tersedia
                    Icon(
                        painter = painterResource(R.drawable.ic_grain), // Memuat sumber daya ikon
                        contentDescription = null, // Tidak ada deskripsi konten
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium)) // Padding di sebelah kiri
                    )
                    Text(
                        text = topic.availableCourses.toString(), // Mengonversi jumlah kursus yang tersedia menjadi string
                        style = MaterialTheme.typography.labelMedium, // Mengatur gaya tipografi
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small)) // Padding di sebelah kiri
                    )
                }
            }
        }
    }
}

// Fungsi preview untuk memvisualisasikan TopicCard dalam pratinjau desain
@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    TambahDaftarTheme {
        // Membuat topik contoh untuk pratinjau
        val topic = Topic(R.string.tambahdata1, 321, R.drawable.kefa)
        Column(
            modifier = Modifier.fillMaxSize(), // Mengisi ukuran yang tersedia
            verticalArrangement = Arrangement.Center, // Memusatkan secara vertikal
            horizontalAlignment = Alignment.CenterHorizontally // Memusatkan secara horizontal
        ) {
            TopicCard(topic = topic) // Menampilkan kartu topik dalam pratinjau
        }
    }
}
