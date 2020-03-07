package com.cqchat.android.k;

/**
 * Created by AiXin on 2019-10-14.
 */

/* renamed from: com.cqchat.android.k.e */
public class Snappy {
    /* renamed from: a */
    private C0107a f837a = C0107a.READY;
    /* renamed from: b */
    private byte f838b;
    /* renamed from: c */
    private int f839c;

    /* compiled from: Snappy */
    /* renamed from: com.cqchat.android.k.e$a */
    private enum C0107a {
        READY,
        READING_PREAMBLE,
        READING_TAG,
        READING_LITERAL,
        READING_COPY
    }

    /* renamed from: a */
    static int m1786a(int i) {
        return ((i << 17) | (i >> 15)) - 1568478504;
    }

    /* renamed from: a */
    public static int m1787a(InputByteBuffer inputByteBuffer) {
        return Snappy.m1788a(inputByteBuffer, inputByteBuffer.mo1830b(), inputByteBuffer.mo1844j());
    }

    /* renamed from: a */
    public static int m1788a(InputByteBuffer inputByteBuffer, int i, int i2) {
        Crc32c crc32c = new Crc32c();
        try {
            crc32c.update(inputByteBuffer.mo1829a(), i, i2);
            int a = Snappy.m1786a((int) crc32c.getValue());
            return a;
        } finally {
            crc32c.reset();
        }
    }

    /* renamed from: a */
    public void mo1857a(InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer) {
        while (inputByteBuffer.mo1833c()) {
            int a;
            switch (this.f837a) {
                case READY:
                    this.f837a = C0107a.READING_PREAMBLE;
                    break;
                case READING_PREAMBLE:
                    break;
                case READING_TAG:
                    break;
                case READING_LITERAL:
                    a = Snappy.m1784a(this.f838b, inputByteBuffer, outputByteBuffer);
                    if (a != -1) {
                        this.f837a = C0107a.READING_TAG;
                        this.f839c += a;
                        continue;
                    } else {
                        return;
                    }
                case READING_COPY:
                    byte b = this.f838b;
                    switch (b & 3) {
                        case 1:
                            a = Snappy.m1785a(b, inputByteBuffer, outputByteBuffer, this.f839c);
                            if (a != -1) {
                                this.f837a = C0107a.READING_TAG;
                                this.f839c += a;
                                break;
                            }
                            return;
                        case 2:
                            a = Snappy.m1794b(b, inputByteBuffer, outputByteBuffer, this.f839c);
                            if (a != -1) {
                                this.f837a = C0107a.READING_TAG;
                                this.f839c += a;
                                break;
                            }
                            return;
                        case 3:
                            a = Snappy.m1799c(b, inputByteBuffer, outputByteBuffer, this.f839c);
                            if (a != -1) {
                                this.f837a = C0107a.READING_TAG;
                                this.f839c += a;
                                break;
                            }
                            return;
                        default:
                            continue;
                    }
                default:
                    continue;
            }
            a = Snappy.m1796b(inputByteBuffer);
            if (a != -1) {
                if (a == 0) {
                    this.f837a = C0107a.READY;
                    return;
                }
                outputByteBuffer.mo1847a(a);
                this.f837a = C0107a.READING_TAG;
                if (inputByteBuffer.mo1833c()) {
                    this.f838b = inputByteBuffer.mo1836e();
                    switch (this.f838b & 3) {
                        case 0:
                            this.f837a = C0107a.READING_LITERAL;
                            break;
                        case 1:
                        case 2:
                        case 3:
                            this.f837a = C0107a.READING_COPY;
                            break;
                        default:
                            continue;
                    }
                } else {
                    return;
                }
            }
            return;
        }
    }

    /* JADX WARNING: Missing block: B:12:0x006a, code skipped:
            com.cqchat.android.p061k.Snappy.m1792a(r0, r1, r7 - r9);
     */
    /* JADX WARNING: Missing block: B:13:0x006f, code skipped:
            r8 = com.cqchat.android.p061k.Snappy.m1789a(r0, r14 + 4, r7 + 4, r2) + 4;
            r9 = r7 + r8;
            com.cqchat.android.p061k.Snappy.m1793a(r1, r7 - r14, r8);
            r0.mo1839f(r17.mo1830b() + r8);
            r7 = r9 - 1;
     */
    /* JADX WARNING: Missing block: B:14:0x0089, code skipped:
            if (r9 < r12) goto L_0x008c;
     */
    /* JADX WARNING: Missing block: B:15:0x008c, code skipped:
            r10 = r9 + 0;
            r4[com.cqchat.android.p061k.Snappy.m1797b(r0, r7, r5)] = (short) (r10 - 1);
            r8 = r7 + 1;
            r11 = com.cqchat.android.p061k.Snappy.m1797b(r0, r8, r5);
            r14 = r4[r11] + 0;
            r4[r11] = (short) r10;
     */
    /* JADX WARNING: Missing block: B:16:0x00ac, code skipped:
            if (r0.mo1831b(r8) == r0.mo1831b(r14)) goto L_0x00b7;
     */
    /* JADX WARNING: Missing block: B:18:0x00b7, code skipped:
            r7 = r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public void mo1858b(InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer) {
        int i;
        int i2;
        InputByteBuffer inputByteBuffer2 = inputByteBuffer;
        OutputByteBuffer outputByteBuffer2 = outputByteBuffer;
        int j = inputByteBuffer.mo1844j();
        int i3 = 0;
        while (true) {
            i = j >>> (i3 * 7);
            if ((i & -128) == 0) {
                break;
            }
            outputByteBuffer2.mo1846a((byte) ((i & 127) | 128));
            i3++;
        }
        outputByteBuffer2.mo1846a((byte) i);
        short[] c = Snappy.m1800c(j);
        i = 32 - ((int) Math.floor(Math.log((double) c.length) / Math.log(2.0d)));
        if (j + 0 >= 15) {
            int i4 = 1;
            int b = Snappy.m1797b(inputByteBuffer2, 1, i);
            i2 = 0;
            /*loop1:
            while (true) {
                int i5 = 32;
                while (true) {
                    int i6 = i5 + 1;
                    i5 = (i5 >> 5) + i4;
                    int i7 = j - 4;
                    if (i5 > i7) {
                        break loop1;
                    }
                    int b2 = Snappy.m1797b(inputByteBuffer2, i5, i);
                    int i8 = c[b] + 0;
                    c[b] = (short) (i4 + 0);
                    if (inputByteBuffer2.mo1831b(i4) == inputByteBuffer2.mo1831b(i8)) {
                        break;
                    }
                    i4 = i5;
                    i5 = i6;
                    b = b2;
                }
                b = Snappy.m1797b(inputByteBuffer2, i4 + 2, i);
                i4 = i2 + 1;
            }*/
        } else {
            i2 = 0;
        }
        if (i2 < j) {
            Snappy.m1792a(inputByteBuffer2, outputByteBuffer2, j - i2);
        }
    }

    /* renamed from: a */
    public void mo1856a() {
        this.f837a = C0107a.READY;
        this.f838b = (byte) 0;
        this.f839c = 0;
    }

    /* renamed from: a */
    static void m1791a(int i, InputByteBuffer inputByteBuffer, int i2, int i3) {
        int a = Snappy.m1788a(inputByteBuffer, i2, i3);
        if (a != i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mismatching checksum: ");
            stringBuilder.append(Integer.toHexString(a));
            stringBuilder.append(" (expected: ");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(')');
            throw new SnappyException(stringBuilder.toString());
        }
    }

    /* renamed from: b */
    private static int m1795b(int i) {
        i = Integer.highestOneBit(i);
        int i2 = 0;
        while (true) {
            i >>= 1;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    /* renamed from: a */
    private static int m1785a(byte b, InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer, int i) {
        if (!inputByteBuffer.mo1833c()) {
            return -1;
        }
        int c = outputByteBuffer.mo1854c();
        int i2 = ((b & 28) >> 2) + 4;
        int h = (((b & 224) << 8) >> 5) | inputByteBuffer.mo1842h();
        Snappy.m1790a(h, i);
        if (h < i2) {
            int i3;
            for (i3 = i2 / h; i3 > 0; i3--) {
                outputByteBuffer.mo1850a(outputByteBuffer.mo1851a(), c - h, h);
            }
            i3 = i2 % h;
            if (i3 != 0) {
                outputByteBuffer.mo1850a(outputByteBuffer.mo1851a(), c - h, i3);
            }
        } else {
            outputByteBuffer.mo1850a(outputByteBuffer.mo1851a(), c - h, i2);
        }
        return i2;
    }

    /* renamed from: b */
    private static int m1794b(byte b, InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer, int i) {
        if (inputByteBuffer.mo1844j() < 2) {
            return -1;
        }
        int c = outputByteBuffer.mo1854c();
        int i2 = ((b >> 2) & 63) + 1;
        int a = Bytes.m1747a(inputByteBuffer.mo1841g());
        Snappy.m1790a(a, i);
        if (a < i2) {
            for (i = i2 / a; i > 0; i--) {
                outputByteBuffer.mo1850a(outputByteBuffer.mo1851a(), c - a, a);
            }
            i = i2 % a;
            if (i != 0) {
                outputByteBuffer.mo1850a(outputByteBuffer.mo1851a(), c - a, i);
            }
        } else {
            outputByteBuffer.mo1850a(outputByteBuffer.mo1851a(), c - a, i2);
        }
        return i2;
    }

    /* renamed from: c */
    private static int m1799c(byte b, InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer, int i) {
        if (inputByteBuffer.mo1844j() < 4) {
            return -1;
        }
        int c = outputByteBuffer.mo1854c();
        int i2 = ((b >> 2) & 63) + 1;
        int a = Bytes.m1746a(inputByteBuffer.mo1838f());
        Snappy.m1790a(a, i);
        InputByteBuffer inputByteBuffer2 = new InputByteBuffer(outputByteBuffer.mo1853b());
        if (a < i2) {
            int i3;
            for (i3 = i2 / a; i3 > 0; i3--) {
                outputByteBuffer.mo1850a(inputByteBuffer2.mo1829a(), c - a, a);
            }
            i3 = i2 % a;
            if (i3 != 0) {
                outputByteBuffer.mo1850a(inputByteBuffer2.mo1829a(), c - a, i3);
            }
        } else {
            outputByteBuffer.mo1850a(inputByteBuffer2.mo1829a(), c - a, i2);
        }
        return i2;
    }

    /* renamed from: a */
    private static int m1784a(byte b, InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer) {
        inputByteBuffer.mo1835d();
        int i = (b >> 2) & 63;
        switch (i) {
            case 60:
                if (inputByteBuffer.mo1833c()) {
                    i = inputByteBuffer.mo1842h();
                    break;
                }
                return -1;
            case 61:
                if (inputByteBuffer.mo1844j() >= 2) {
                    i = Bytes.m1747a(inputByteBuffer.mo1841g());
                    break;
                }
                return -1;
            case 62:
                if (inputByteBuffer.mo1844j() >= 3) {
                    i = Bytes.m1748b(inputByteBuffer.mo1843i());
                    break;
                }
                return -1;
            case 64:
                if (inputByteBuffer.mo1844j() >= 4) {
                    i = Bytes.m1746a(inputByteBuffer.mo1838f());
                    break;
                }
                return -1;
        }
        i++;
        if (inputByteBuffer.mo1844j() < i) {
            inputByteBuffer.mo1845k();
            return -1;
        }
        outputByteBuffer.mo1850a(inputByteBuffer.mo1829a(), inputByteBuffer.mo1830b(), i);
        inputByteBuffer.mo1839f(inputByteBuffer.mo1830b() + i);
        return i;
    }

    /* renamed from: a */
    private static void m1793a(OutputByteBuffer outputByteBuffer, int i, int i2) {
        while (i2 >= 68) {
            Snappy.m1798b(outputByteBuffer, i, 64);
            i2 -= 64;
        }
        if (i2 > 64) {
            Snappy.m1798b(outputByteBuffer, i, 60);
            i2 -= 60;
        }
        Snappy.m1798b(outputByteBuffer, i, i2);
    }

    /* renamed from: b */
    private static void m1798b(OutputByteBuffer outputByteBuffer, int i, int i2) {
        if (i2 >= 12 || i >= 2048) {
            outputByteBuffer.mo1846a((byte) (((i2 - 1) << 2) | 2));
            outputByteBuffer.mo1846a((byte) (i & 255));
            outputByteBuffer.mo1846a((byte) ((i >> 8) & 255));
            return;
        }
        outputByteBuffer.mo1846a((byte) ((((i2 - 4) << 2) | 1) | ((i >> 8) << 5)));
        outputByteBuffer.mo1846a((byte) (i & 255));
    }

    /* renamed from: a */
    private static void m1792a(InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer, int i) {
        if (i < 61) {
            outputByteBuffer.mo1846a((byte) ((i - 1) << 2));
        } else {
            int i2 = i - 1;
            int b = (Snappy.m1795b(i2) / 8) + 1;
            outputByteBuffer.mo1846a((byte) ((b + 59) << 2));
            for (int i3 = 0; i3 < b; i3++) {
                outputByteBuffer.mo1846a((byte) ((i2 >> (i3 * 8)) & 255));
            }
        }
        outputByteBuffer.mo1850a(inputByteBuffer.mo1829a(), inputByteBuffer.mo1830b(), i);
        inputByteBuffer.mo1839f(inputByteBuffer.mo1830b() + i);
    }

    /* renamed from: a */
    private static int m1789a(InputByteBuffer inputByteBuffer, int i, int i2, int i3) {
        int i4 = 0;
        while (i2 <= i3 - 4 && inputByteBuffer.mo1831b(i2) == inputByteBuffer.mo1831b(i + i4)) {
            i2 += 4;
            i4 += 4;
        }
        while (i2 < i3 && inputByteBuffer.mo1827a(i + i4) == inputByteBuffer.mo1827a(i2)) {
            i2++;
            i4++;
        }
        return i4;
    }

    /* renamed from: c */
    private static short[] m1800c(int i) {
        int i2 = 256;
        while (i2 < 16384 && i2 < i) {
            i2 <<= 1;
        }
        if (i2 <= 256) {
            return new short[256];
        }
        return new short[16384];
    }

    /* renamed from: b */
    private static int m1797b(InputByteBuffer inputByteBuffer, int i, int i2) {
        return (inputByteBuffer.mo1831b(i) + 506832829) >>> i2;
    }

    /* renamed from: b */
    private static int m1796b(InputByteBuffer inputByteBuffer) {
        int i = 0;
        int i2 = 0;
        while (inputByteBuffer.mo1833c()) {
            short h = inputByteBuffer.mo1842h();
            int i3 = i2 + 1;
            i |= (h & 127) << (i2 * 7);
            if ((h & 128) == 0) {
                return i;
            }
            if (i3 < 4) {
                i2 = i3;
            } else {
                throw new SnappyException("Preamble is greater than 4 bytes");
            }
        }
        return 0;
    }

    /* renamed from: a */
    private static void m1790a(int i, int i2) {
        if (i > 32767) {
            throw new SnappyException("Offset exceeds maximum permissible value");
        } else if (i <= 0) {
            throw new SnappyException("Offset is less than minimum permissible value");
        } else if (i > i2) {
            throw new SnappyException("Offset exceeds size of chunk");
        }
    }
}