
		JPanel namaKelCPanel = new JPanel();
		cPanel.add(namaKelCPanel);

		JButton namaKelompok = new JButton("Nama Kelompok");
        namaKelompok.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(namaKelCPanel,
                        "Affan Dhia Ardiva\n Dimas Saputra\n Felicia\n Grace Angelica\n Nur Intan Alatas\n Satria Bagus Wicaksono\n Stephen Jaya Gunawan");
            }
        });
		namaKelCPanel.add(namaKelompok);
