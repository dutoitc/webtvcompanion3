package ch.mno.wc3.services.facebook.requests;

// curl -X POST \
//  "https://graph-video.facebook.com/v15.0/1755847768034402/videos"  \
//  -F "upload_phase=transfer" \
//  -F "upload_session_id=2918040901584241" \
//  -F "access_token=EAADI..." \
//  -F "start_offset=10485760" \
//  -F "video_file_chunk=@/Users/...xab"
//
// {
//  "start_offset":"20971520",  //Value for third chunk
//  "end_offset":"22420886"
//}
// when start matches end, we can close request

public class VideoUploadNextChunksRequest {
}
