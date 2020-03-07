package com.cqchat.android.k;

/**
 * Created by AiXin on 2019-10-14.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.cqchat.android.k.g */
public class SnappyFramedDecoder {
    /* renamed from: a */
    private static final byte[] f847a = new byte[]{(byte) 115, (byte) 78, (byte) 97, (byte) 80, (byte) 112, (byte) 89};
    /* renamed from: b */
    private final Snappy f848b;
    /* renamed from: c */
    private final boolean f849c;
    /* renamed from: d */
    private boolean f850d;
    /* renamed from: e */
    private boolean f851e;

    /* compiled from: SnappyFramedDecoder */
    /* renamed from: com.cqchat.android.k.g$a */
    enum C0109a {
        STREAM_IDENTIFIER,
        COMPRESSED_DATA,
        UNCOMPRESSED_DATA,
        RESERVED_UNSKIPPABLE,
        RESERVED_SKIPPABLE
    }

    public SnappyFramedDecoder() {
        this(false);
    }

    public SnappyFramedDecoder(boolean z) {
        this.f848b = new Snappy();
        this.f849c = z;
    }

    /* renamed from: a */
    public byte[] mo1860a(byte[] bArr) throws Exception {
        InputByteBuffer inputByteBuffer = new InputByteBuffer(bArr);
        ArrayList<byte[]> arrayList = new ArrayList();
        while (inputByteBuffer.mo1833c()) {
            int size = arrayList.size();
            int j = inputByteBuffer.mo1844j();
            mo1859a(inputByteBuffer, arrayList);
            if (size == arrayList.size()) {
                if (j == inputByteBuffer.mo1844j()) {
                    break;
                }
            } else if (j == inputByteBuffer.mo1844j()) {
                throw new SnappyException(".decode() did not read anything but decoded a message.");
            }
        }
        int i = 0;
        for (byte[] length : arrayList) {
            i += length.length;
        }
        OutputByteBuffer outputByteBuffer = new OutputByteBuffer(i);
        for (byte[] a : arrayList) {
            outputByteBuffer.mo1849a(a);
        }
        return outputByteBuffer.mo1853b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1859a(InputByteBuffer inputByteBuffer, List<byte[]> list) throws Exception {
        if (this.f851e) {
            inputByteBuffer.mo1840g(inputByteBuffer.mo1844j());
            return;
        }
        try {
            int b = inputByteBuffer.mo1830b();
            int j = inputByteBuffer.mo1844j();
            if (j >= 4) {
                short c = inputByteBuffer.mo1832c(b);
                C0109a a = SnappyFramedDecoder.m1804a((byte) c);
                b = Bytes.m1748b(inputByteBuffer.mo1834d(b + 1));
                StringBuilder stringBuilder;
                switch (a) {
                    case STREAM_IDENTIFIER:
                        if (b != f847a.length) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Unexpected length of stream identifier: ");
                            stringBuilder.append(b);
                            throw new SnappyException(stringBuilder.toString());
                        } else if (j < f847a.length + 4) {
                            break;
                        } else {
                            byte[] bArr = new byte[b];
                            inputByteBuffer.mo1840g(4).mo1828a(bArr);
                            if (Arrays.equals(bArr, f847a)) {
                                this.f850d = true;
                                break;
                            }
                            throw new SnappyException("Unexpected stream identifier contents. Mismatched snappy protocol version?");
                        }
                    case RESERVED_SKIPPABLE:
                        if (this.f850d) {
                            b += 4;
                            if (j >= b) {
                                inputByteBuffer.mo1840g(b);
                                break;
                            }
                            return;
                        }
                        throw new SnappyException("Received RESERVED_SKIPPABLE tag before STREAM_IDENTIFIER");
                    case RESERVED_UNSKIPPABLE:
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Found reserved unskippable chunk type: 0x");
                        stringBuilder.append(Integer.toHexString(c));
                        throw new SnappyException(stringBuilder.toString());
                    case UNCOMPRESSED_DATA:
                        if (!this.f850d) {
                            throw new SnappyException("Received UNCOMPRESSED_DATA tag before STREAM_IDENTIFIER");
                        } else if (b <= 65540) {
                            if (j >= b + 4) {
                                inputByteBuffer.mo1840g(4);
                                if (this.f849c) {
                                    Snappy.m1791a(Bytes.m1746a(inputByteBuffer.mo1838f()), inputByteBuffer, inputByteBuffer.mo1830b(), b - 4);
                                } else {
                                    inputByteBuffer.mo1840g(4);
                                }
                                list.add(inputByteBuffer.mo1837e(b - 4).mo1829a());
                                break;
                            }
                            return;
                        } else {
                            throw new SnappyException("Received UNCOMPRESSED_DATA larger than 65540 bytes");
                        }
                    case COMPRESSED_DATA:
                        if (this.f850d) {
                            if (j >= b + 4) {
                                inputByteBuffer.mo1840g(4);
                                j = Bytes.m1746a(inputByteBuffer.mo1838f());
                                OutputByteBuffer outputByteBuffer = new OutputByteBuffer();
                                this.f848b.mo1857a(inputByteBuffer.mo1837e(b - 4), outputByteBuffer);
                                if (this.f849c) {
                                    Snappy.m1791a(j, new InputByteBuffer(outputByteBuffer.mo1851a()), 0, outputByteBuffer.mo1854c());
                                }
                                list.add(outputByteBuffer.mo1853b());
                                this.f848b.mo1856a();
                                break;
                            }
                            return;
                        }
                        throw new SnappyException("Received COMPRESSED_DATA tag before STREAM_IDENTIFIER");
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            this.f851e = true;
            throw e;
        }
    }

    /* renamed from: a */
    static C0109a m1804a(byte b) {
        if (b == (byte) 0) {
            return C0109a.COMPRESSED_DATA;
        }
        if (b == (byte) 1) {
            return C0109a.UNCOMPRESSED_DATA;
        }
        if (b == (byte) -1) {
            return C0109a.STREAM_IDENTIFIER;
        }
        if ((b & 128) == 128) {
            return C0109a.RESERVED_SKIPPABLE;
        }
        return C0109a.RESERVED_UNSKIPPABLE;
    }
}