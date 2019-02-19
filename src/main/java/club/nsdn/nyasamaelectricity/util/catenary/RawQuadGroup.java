package club.nsdn.nyasamaelectricity.util.catenary;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class RawQuadGroup implements IRawModel<RawQuadGroup> {
    private final LinkedList<IRawElement> elements = new LinkedList<>();

    public RawQuadGroup() {
    }

    public RawQuadGroup(IRawElement... rawModels) {
        this.add(rawModels);
    }

    public void add(IRawElement... rawModels) {
        for (IRawElement rawModel : rawModels) {
            this.elements.add(rawModel);
        }
    }

    public void merge(RawQuadGroup group) {
        elements.addAll(group.elements);
    }

    @Override
    public RawQuadGroup clone() {
        RawQuadGroup ret = new RawQuadGroup();
        for (IRawElement part : this.elements)
            ret.add(part.clone());
        return ret;
    }

    @Override
    public RawQuadGroup translateCoord(float x, float y, float z) {
        for (IRawModel part : this.elements)
            part.translateCoord(x, y, z);

        return this;
    }

    public RawQuadGroup translateCoord(Vec3d offset) {
    	return this.translateCoord((float) offset.x, (float) offset.y, (float) offset.z);
    }

    @Override
    public RawQuadGroup rotateAroundX(float angle) {
        for (IRawModel part : this.elements)
            part.rotateAroundX(angle);

        return this;
    }

    @Override
    public RawQuadGroup rotateAroundY(float angle) {
        for (IRawModel part : this.elements)
            part.rotateAroundY(angle);

        return this;
    }

    @Override
    public RawQuadGroup rotateAroundZ(float angle) {
        for (IRawModel part : this.elements)
            part.rotateAroundZ(angle);

        return this;
    }

    @Override
    public RawQuadGroup rotateToVec(float xStart, float yStart, float zStart,
                                    float xEnd, float yEnd, float zEnd) {
        for (IRawModel part : this.elements)
            part.rotateToVec(xStart, yStart, zStart, xEnd, yEnd, zEnd);

        return this;
    }

    public RawQuadGroup rotateToVec(Vec3d vec1, Vec3d vec2) {
    	return this.rotateToVec((float) vec1.x, (float) vec1.y, (float) vec1.z, (float) vec2.x, (float) vec2.y, (float) vec2.z);
    }

    @Override
    public RawQuadGroup rotateToDirection(EnumFacing direction) {
        for (IRawModel part : this.elements)
            part.rotateToDirection(direction);
        return this;
    }

    @Override
    public RawQuadGroup rotateAroundVector(float angle, float x, float y, float z) {
        for (IRawModel part : this.elements)
            part.rotateAroundVector(angle, x, y, z);

        return this;
    }

    public RawQuadGroup rotateAroundVector(float angle, Vec3d vec) {
    	return this.rotateAroundVector(angle, (float) vec.x, (float) vec.y, (float) vec.z);
    }

	@Override
	public RawQuadGroup scale(float scale) {
		for (IRawModel part : this.elements)
			part.scale(scale);
		return this;
	}
    
    @Override
    public void bake(List<BakedQuad> list) {
        for (IRawModel part : this.elements)
            part.bake(list);
    }
}
