package com.cqchat.android.k;

import vip.xposed.liaobei.Utils.MStringUtils;

/**
 * Created by AiXin on 2019-10-14.
 */

/* renamed from: com.cqchat.android.k.h */
public class SnappyFramedEncoder {
    /* renamed from: a */
    private static final byte[] f852a = new byte[]{(byte) -1, (byte) 6, (byte) 0, (byte) 0, (byte) 115, (byte) 78, (byte) 97, (byte) 80, (byte) 112, (byte) 89};
    /* renamed from: b */
    private final Snappy f853b = new Snappy();
    /* renamed from: c */
    private boolean f854c;

    /* renamed from: a */
    public byte[] mo1861a(byte[] bArr) throws Exception {
        InputByteBuffer inputByteBuffer = new InputByteBuffer(bArr);
        OutputByteBuffer outputByteBuffer = new OutputByteBuffer();
        m1807a(inputByteBuffer, outputByteBuffer);
        return outputByteBuffer.mo1853b();
    }

    /* renamed from: a */
    private void m1807a(InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer) throws Exception {
        if (inputByteBuffer.mo1833c()) {
            if (!this.f854c) {
                this.f854c = true;
                outputByteBuffer.mo1849a(f852a);
            }
            int j = inputByteBuffer.mo1844j();
            if (j > 18) {
                while (true) {
                    int c = outputByteBuffer.mo1854c() + 1;
                    if (j >= 18) {
                        outputByteBuffer.mo1852b(0);
                        if (j <= 32767) {
                            inputByteBuffer = inputByteBuffer.mo1837e(j);
                            SnappyFramedEncoder.m1810b(inputByteBuffer, outputByteBuffer);
                            System.out.println(MStringUtils.bytesToHex(outputByteBuffer.mo1853b(),false));

                            this.f853b.mo1858b(inputByteBuffer, outputByteBuffer);
                            System.out.println(MStringUtils.bytesToHex(outputByteBuffer.mo1853b(),false));

                            SnappyFramedEncoder.m1809a(outputByteBuffer, c);
                            System.out.println(MStringUtils.bytesToHex(outputByteBuffer.mo1853b(),false));
                            break;
                        }
                        InputByteBuffer e = inputByteBuffer.mo1837e(32767);
                        SnappyFramedEncoder.m1810b(e, outputByteBuffer);
                        System.out.println(MStringUtils.bytesToHex(outputByteBuffer.mo1851a(),false));
                        this.f853b.mo1858b(e, outputByteBuffer);
                        System.out.println(MStringUtils.bytesToHex(outputByteBuffer.mo1851a(),false));
                        SnappyFramedEncoder.m1809a(outputByteBuffer, c);
                        j -= 32767;
                    } else {
                        SnappyFramedEncoder.m1808a(inputByteBuffer.mo1837e(j), outputByteBuffer, j);
                        break;
                    }
                }
            }else{
                SnappyFramedEncoder.m1808a(inputByteBuffer, outputByteBuffer, j);
            }
        }
    }

    /* renamed from: b */
    private static void m1810b(InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer) {
        outputByteBuffer.mo1852b(Bytes.m1746a(Snappy.m1787a(inputByteBuffer)));
    }

    /* renamed from: a */
    private static void m1809a(OutputByteBuffer outputByteBuffer, int i) {
        int c = (outputByteBuffer.mo1854c() - i) - 3;
        if ((c >>> 24) == 0) {
            outputByteBuffer.mo1848a(i, Bytes.m1748b(c));
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("compressed data too large: ");
        stringBuilder.append(c);
        throw new SnappyException(stringBuilder.toString());
    }

    /* renamed from: b */
    private static void m1811b(OutputByteBuffer outputByteBuffer, int i) {
        outputByteBuffer.mo1855c(Bytes.m1748b(i));
    }

    /* renamed from: a */
    private static void m1808a(InputByteBuffer inputByteBuffer, OutputByteBuffer outputByteBuffer, int i) {
        outputByteBuffer.mo1846a((byte) 1);
        SnappyFramedEncoder.m1811b(outputByteBuffer, i + 4);
        SnappyFramedEncoder.m1810b(inputByteBuffer, outputByteBuffer);
        outputByteBuffer.mo1850a(inputByteBuffer.mo1829a(), inputByteBuffer.mo1830b(), i);
    }
}